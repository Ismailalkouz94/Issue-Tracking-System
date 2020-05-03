package com.ismail.issuetracking.controller;

import com.ismail.issuetracking.dto.ChangePasswordDTO;
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
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("")
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

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> editUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {

        ResponseMessage responseMessage = ResponseMessage.getInstance();
        try {
            userDTO.setId(id);
            responseMessage.setResponse(userService.edit(userDTO));
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
    public ResponseMessage getUserById(@PathVariable Long id) {
        ResponseMessage responseMessage = ResponseMessage.getInstance();
        try {
            responseMessage.setResponse(userService.findById(id));
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

    @GetMapping("/roles")
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

    @GetMapping("/postions")
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

    @PostMapping("/{id}/changePassword")
    public ResponseEntity<ResponseMessage> addUser(@PathVariable Long id, @RequestBody ChangePasswordDTO changePasswordDTO) {

        ResponseMessage responseMessage = ResponseMessage.getInstance();
        try {
            changePasswordDTO.setUserId(id);
            userService.changePassword(changePasswordDTO);
//            responseMessage.setResponse();
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

}
