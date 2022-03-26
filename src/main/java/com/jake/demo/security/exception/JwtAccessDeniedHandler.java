package com.jake.demo.security.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


// Exceptions when used to resolve authenticated users access non-authority resources

public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    /**
     * When the user tries to access the REST resource that requires permissions, the permissions are insufficient,
     * This method will call 403 response and error message
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        accessDeniedException = new AccessDeniedException("Sorry you don not enough permissions to access it!");
        response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
    }
}
