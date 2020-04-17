package com.ismail.issuetracking.config;

import java.util.LinkedHashMap;
import java.util.Map;

import com.ismail.issuetracking.model.ResponseMessage;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.WebRequest;

@Configuration
public class ExceptionHandlerConfig {

    //private static final String DEFAULT_KEY_TIMESTAMP = "timestamp";
    private static final String DEFAULT_KEY_STATUS = "status";
    private static final String DEFAULT_KEY_ERROR = "error";
    private static final String DEFAULT_KEY_ERRORS = "errors";
    private static final String DEFAULT_KEY_MESSAGE = "message";
    //private static final String DEFAULT_KEY_PATH = "path";

    //

    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {

            @Override
            public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
                Map<String, Object> defaultMap = super.getErrorAttributes(webRequest, includeStackTrace);

                Map<String, Object> errorAttributes = new LinkedHashMap<>();
                errorAttributes.put("response", null);
                errorAttributes.put("success", defaultMap.get(DEFAULT_KEY_STATUS).equals(200));
                errorAttributes.put("errMsg", defaultMap.get(DEFAULT_KEY_ERROR));
                errorAttributes.put("errDetail", defaultMap.get(DEFAULT_KEY_MESSAGE));

                errorAttributes.put("successMsg", null);
                return errorAttributes;
            }
        };
    }
}
