package com.ismail.issuetracking.config.jwt;

import com.ismail.issuetracking.exception.IssueTrackingException;
import com.ismail.issuetracking.model.ResponseMessage;
import com.ismail.issuetracking.util.Constants;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -7858869558953243875L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        final String expired = (String) request.getAttribute(Constants.TOKEN_EXPIRED);
        if (expired != null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, Constants.EXPIRED_TOKEN_ERR_MSG);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Login details");
        }
    }
}
