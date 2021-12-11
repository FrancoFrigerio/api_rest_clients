package ar.com.frigeriofranco.practic.controller.handlecontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Value("error.access.denied")
    private String errorAccessDenied;

    @Autowired
    MessageSource mesaMessageSource;


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        Map<String, String> error = new HashMap<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            log.info("User-->" + authentication.getName() + request.getRequestURI());
        }
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(SC_FORBIDDEN);
        error.put("error_accesDenied", e.getMessage());
        error.put("message", mesaMessageSource.getMessage(errorAccessDenied,null, Locale.getDefault()));
        response.getWriter().write(new ObjectMapper().writeValueAsString(error));
    }
}