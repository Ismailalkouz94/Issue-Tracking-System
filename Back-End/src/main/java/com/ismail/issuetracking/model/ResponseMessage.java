package com.ismail.issuetracking.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class ResponseMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean isSuccess;
    private Object response;
    private String errMsg;
    private String successMsg;

    private static ResponseMessage responseMessage = null;

    public static ResponseMessage getInstance() {
        if (responseMessage == null) {
            responseMessage = new ResponseMessage();
        }
        responseMessage.setResponse(null);
        responseMessage.setErrMsg(null);
        responseMessage.setSuccessMsg(null);

        return responseMessage;
    }

}
