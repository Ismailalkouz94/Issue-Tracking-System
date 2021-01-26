package com.ismail.issuetracking.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode
@Builder
public class ResponseMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean isSuccess;
    private Object response;
    private String errMsg;
    private String errDetail;
    private String successMsg;

    private static ResponseMessage responseMessage = null;

    public static ResponseMessage getInstance() {
        if (responseMessage == null) {
            responseMessage = new ResponseMessage();
        }
        responseMessage.setResponse(null);
        responseMessage.setErrMsg(null);
        responseMessage.setErrDetail(null);
        responseMessage.setSuccessMsg(null);

        return responseMessage;
    }

}
