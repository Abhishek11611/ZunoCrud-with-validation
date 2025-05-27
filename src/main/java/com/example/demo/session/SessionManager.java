package com.example.demo.session;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.entity.SessionEntity;
import com.example.demo.entity.User;
import com.example.demo.repository.SessionRepository;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SessionManager extends OncePerRequestFilter {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String sessionId = request.getHeader("SessionId");
        sessionId=AESUtil.decrypt(sessionId);

        if (sessionId == null || sessionId.isEmpty()) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Please SessionId");
            return;
        }

        Optional<SessionEntity> sessionOpt = sessionRepository.findBySessionId(sessionId);

        if (sessionOpt.isEmpty()) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid SessionId");
            return;
        }

        SessionEntity session = sessionOpt.get();

        if (session.getExpiryDate() == null || session.getExpiryDate().isBefore(LocalDateTime.now())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Session has expired");
            return;
        }

        Optional<User> userOpt = userRepository.findById(session.getUserId());

        if (userOpt.isEmpty()) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "User not found for the session");
            return;
        }

        User user = userOpt.get();

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                user, null, Collections.emptyList());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    // this method for don't filter all this request
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();

        return path.startsWith("/auth/register") ||
               path.startsWith("/auth/login") ||
               path.startsWith("/swagger-ui.html") ||
               path.startsWith("/swagger-ui") ||
               path.startsWith("/v3/api-docs");
    }
}
