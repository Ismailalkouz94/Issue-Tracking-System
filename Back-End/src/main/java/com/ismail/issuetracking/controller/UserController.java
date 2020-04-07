package com.ismail.issuetracking.controller;

import com.ismail.issuetracking.dto.UserDTO;
import com.ismail.issuetracking.entity.User;
import com.ismail.issuetracking.exception.IssueTrackingException;
import com.ismail.issuetracking.model.ResponseMessage;
import com.ismail.issuetracking.service.UserService;
import com.ismail.issuetracking.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/add")
    public ResponseEntity<ResponseMessage> addUser(@RequestBody UserDTO userDTO) {

        ResponseMessage responseMessage = ResponseMessage.getInstance();
        try {
            responseMessage.setResponse(userService.add(userDTO));
            responseMessage.setSuccess(true);
        } catch (IssueTrackingException e) {
            responseMessage.setSuccess(false);
            responseMessage.setErrMsg(e.getMessage());
        } catch (Exception e) {
            responseMessage.setSuccess(false);
            responseMessage.setErrMsg(e.getMessage());
        }
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/{userName}")
    public ResponseMessage getUser(@PathVariable String userName) {
        ResponseMessage responseMessage = ResponseMessage.getInstance();
        try {
            responseMessage.setResponse(userService.findByUserName(userName));
            responseMessage.setSuccess(true);
        } catch (IssueTrackingException e) {
            responseMessage.setSuccess(false);
            responseMessage.setErrMsg(e.getMessage());
        } catch (Exception e) {
            responseMessage.setSuccess(false);
            responseMessage.setErrMsg(e.getMessage());
        }
        return responseMessage;
    }


    @GetMapping("")
    public ResponseMessage getAllUser() {
        ResponseMessage responseMessage = ResponseMessage.getInstance();
        try {
            responseMessage.setResponse(userService.find());
            responseMessage.setSuccess(true);
        } catch (IssueTrackingException e) {
            responseMessage.setSuccess(false);
            responseMessage.setErrMsg(e.getMessage());
        } catch (Exception e) {
            responseMessage.setSuccess(false);
            responseMessage.setErrMsg(e.getMessage());
        }
        return responseMessage;
    }

    @GetMapping("/id/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage delete(@PathVariable Long id) {
        ResponseMessage responseMessage = ResponseMessage.getInstance();
        try {
            responseMessage.setResponse(userService.delete(id));
            responseMessage.setSuccess(true);
        } catch (IssueTrackingException e) {
            responseMessage.setSuccess(false);
            responseMessage.setErrMsg(e.getMessage());
        } catch (Exception e) {
            responseMessage.setSuccess(false);
            responseMessage.setErrMsg(e.getMessage());
        }
        return responseMessage;
    }

    @GetMapping("/role")
    public ResponseMessage getRole() {
        ResponseMessage responseMessage = ResponseMessage.getInstance();
        try {
            responseMessage.setResponse(userService.findRoles());
            responseMessage.setSuccess(true);
        } catch (IssueTrackingException e) {
            responseMessage.setSuccess(false);
            responseMessage.setErrMsg(e.getMessage());
        } catch (Exception e) {
            responseMessage.setSuccess(false);
            responseMessage.setErrMsg(e.getMessage());
        }
        return responseMessage;
    }

    @GetMapping("/postion")
    public ResponseMessage getPosyion() {
        ResponseMessage responseMessage = ResponseMessage.getInstance();
        try {
            responseMessage.setResponse(userService.findPositions());
            responseMessage.setSuccess(true);
        } catch (IssueTrackingException e) {
            responseMessage.setSuccess(false);
            responseMessage.setErrMsg(e.getMessage());
        } catch (Exception e) {
            responseMessage.setSuccess(false);
            responseMessage.setErrMsg(e.getMessage());
        }
        return responseMessage;
    }

}
