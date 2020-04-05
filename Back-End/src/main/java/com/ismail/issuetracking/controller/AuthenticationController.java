package com.ismail.issuetracking.controller;

import com.ismail.issuetracking.config.jwt.JwtTokenUtil;
import com.ismail.issuetracking.entity.User;
import com.ismail.issuetracking.exception.IssueTrackingException;
import com.ismail.issuetracking.model.JwtResponse;
import com.ismail.issuetracking.model.LoginResponse;
import com.ismail.issuetracking.model.ResponseMessage;
import com.ismail.issuetracking.service.UserService;
import com.ismail.issuetracking.service.impl.MyUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;


    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(User user) throws Exception {

        ResponseMessage responseMessage = ResponseMessage.getInstance();
        try {
            authenticate(user.getUserName(), user.getPassword());
            final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
            final String token = jwtTokenUtil.generateToken(userDetails);

            user = userService.findByUserName(user.getUserName());
            JwtResponse jwtResponse = new JwtResponse(token);
            responseMessage.setResponse(new LoginResponse(user, jwtResponse));
        } catch (IssueTrackingException e) {
            responseMessage.setSuccess(false);
            responseMessage.setErrMsg(e.getMessage());
        } catch (Exception e) {
            responseMessage.setSuccess(false);
            responseMessage.setErrMsg(e.getMessage());
        }
        return ResponseEntity.ok(responseMessage);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping("/errorLogin")
    public ResponseEntity<?> success(User user) {
        ResponseMessage responseMessage = ResponseMessage.getInstance();
        try {
            authenticate(user.getUserName(),user.getPassword());
        } catch (ExpiredJwtException e) {
            responseMessage.setSuccess(false);
            responseMessage.setErrMsg(e.getMessage());
        } catch (Exception e) {
            responseMessage.setErrMsg(e.getMessage());
            responseMessage.setSuccess(false);
        }
        return new ResponseEntity(responseMessage, HttpStatus.UNAUTHORIZED);
    }
}
