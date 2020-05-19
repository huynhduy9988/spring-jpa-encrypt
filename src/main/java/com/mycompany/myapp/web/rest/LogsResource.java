package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.model.Users;
import com.mycompany.myapp.repository.UserRepository;
import com.mycompany.myapp.web.rest.vm.LoggerVM;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for view and managing Log Level at runtime.
 */
@RestController
@RequestMapping("/management")
public class LogsResource {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/logs")
    @Timed
    public List<LoggerVM> getList() {

        Users users = new Users();
        users.setCustomerNo("111");
        users.setGmailAddress("duyhuynh61@gmail.com");

        userRepository.save(users);

        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        return context.getLoggerList()
            .stream()
            .map(LoggerVM::new)
            .collect(Collectors.toList());
    }

    @PutMapping("/logs")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Timed
    public void changeLevel(@RequestBody LoggerVM jsonLogger) {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        context.getLogger(jsonLogger.getName()).setLevel(Level.valueOf(jsonLogger.getLevel()));

        Users users = new Users();
        users.setCustomerNo("111");
        users.setGmailAddress("duyhuynh61@gmail.com");

        userRepository.save(users);
    }
}
