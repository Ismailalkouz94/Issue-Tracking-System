package com.ismail.issuetracking.controller;

import com.ismail.issuetracking.dto.IssueDTO;
import com.ismail.issuetracking.exception.IssueTrackingException;
import com.ismail.issuetracking.model.ResponseMessage;
import com.ismail.issuetracking.service.IssuesService;
import com.ismail.issuetracking.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/issues")
public class IssuesController {

    @Autowired
    private IssuesService issuesService;

//    @CachePut(value = "userIssuesFilter", key = "#issueDTO.owner")
    @PostMapping("")
    public ResponseMessage add(@RequestBody IssueDTO issueDTO) {
        try {
            return ResponseMessage.builder().
                    isSuccess(true).
                    response(issuesService.add(issueDTO)).
                    successMsg(Constants.ISSUE_ADDED_SUCCESSFULLY).
                    build();
        } catch (IssueTrackingException e) {
            return ResponseMessage.builder().isSuccess(false).errMsg(e.getMessage()).build();
        } catch (Exception e) {
            return ResponseMessage.builder().isSuccess(false).errMsg(e.getMessage()).build();
        }
    }

//    @CachePut(value = "userIssues", key = "#issueDTO.id")
    @PutMapping("/{id}")
    public ResponseMessage edit(@PathVariable Long id,@RequestBody IssueDTO issueDTO) {
        ResponseMessage responseMessage = ResponseMessage.getInstance();
        try {
            issueDTO.setId(id);
            responseMessage.setResponse(issuesService.edit(issueDTO));
            responseMessage.setSuccessMsg(Constants.ISSUE_EDITED_SUCCESSFULLY);
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

//    @CacheEvict(value = "userIssues", allEntries=true)
    @DeleteMapping("/{id}")
    public ResponseMessage delete(@PathVariable Long id) {
        ResponseMessage responseMessage = ResponseMessage.getInstance();
        try {
            issuesService.delete(id);
            responseMessage.setSuccess(true);
            responseMessage.setSuccessMsg(Constants.ISSUE_DELETED_SUCCESSFULLY);
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
    public ResponseMessage get() {
        ResponseMessage responseMessage = ResponseMessage.getInstance();
        try {
            responseMessage.setResponse(issuesService.findAll());
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

    @GetMapping("/{id}")
    public ResponseMessage findById(@PathVariable Long id) {
        ResponseMessage responseMessage = ResponseMessage.getInstance();
        try {
            responseMessage.setResponse(issuesService.find(id));
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

//    @Cacheable(value = "userIssues", key = "#userId")
    @GetMapping("/user/{userId}")
    public ResponseMessage getByUser(@PathVariable Long userId) {
        ResponseMessage responseMessage = ResponseMessage.getInstance();
        try {
            responseMessage.setResponse(issuesService.findByUser(userId));
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

//    @Cacheable(value = "userIssuesFilter", key = "#userId")
    @GetMapping("/users/{userId}/filter/{filterId}")
    public ResponseMessage filter(@PathVariable Long userId,@PathVariable int filterId) {
        try {
            return ResponseMessage.builder().
                    isSuccess(true).
                    response(issuesService.issuesFilter(userId,filterId)).
                    build();
        } catch (IssueTrackingException e) {
            return ResponseMessage.builder().isSuccess(false).errMsg(e.getMessage()).build();
        } catch (Exception e) {
            return ResponseMessage.builder().isSuccess(false).errMsg(e.getMessage()).build();
        }
    }

    @GetMapping("/assinged/{id}")
    public ResponseMessage getByAssinged(@PathVariable Long id) {
        ResponseMessage responseMessage = ResponseMessage.getInstance();
        try {
            responseMessage.setResponse(issuesService.findByAssigned(id));
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

    @GetMapping("/types")
    public ResponseMessage getIssueTypes() {
        ResponseMessage responseMessage = ResponseMessage.getInstance();
        try {
            responseMessage.setResponse(issuesService.findAllTypes());
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

    @GetMapping("/status")
    public ResponseMessage getIssueStatus() {
        ResponseMessage responseMessage = ResponseMessage.getInstance();
        try {
            responseMessage.setResponse(issuesService.findAllStatus());
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
