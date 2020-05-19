package com.mycompany.myapp.model;

import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "customer_no")
    private String customerNo;

    @Column(name="gmail_address")
    @ColumnTransformer(
        read = "AES_DECRYPT(UNHEX(gmail_address), '${encryption.key}')",
        write = "HEX(AES_ENCRYPT(?, '${encryption.key}'))")
    private String gmailAddress;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getGmailAddress() {
        return gmailAddress;
    }

    public void setGmailAddress(String gmailAddress) {
        this.gmailAddress = gmailAddress;
    }
}
