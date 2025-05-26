//package com.example.demo.jwt;
//
//import java.io.IOException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.SignatureException;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@Component
//public class JwtRequestFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private MyUserDetailsService userDetailsService;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//			throws ServletException, IOException {
//
//		final String authorizationHeader = request.getHeader("Authorization");
//		String username = null;
//		String jwt = null;
//
//		try {
//
//			if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//				jwt = authorizationHeader.substring(7);
//				username = jwtUtil.extractUsername(jwt);
//			}
//
//			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//				UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//
//				if (jwtUtil.validateToken(jwt, userDetails)) {
//					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//							userDetails, null, userDetails.getAuthorities());
//					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//					SecurityContextHolder.getContext().setAuthentication(authentication);
//				}
//			}
//			
//			chain.doFilter(request, response);
//
//		} catch (ExpiredJwtException e) {
//	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//	        response.setContentType(jwt);
//	        response.getWriter().write("Token has expired");
//	        response.getWriter().flush();
//	    } catch (MalformedJwtException e) {
//	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//	        response.getWriter().write("Invalid JWT token");
//	        response.getWriter().flush();
//	    } catch (SignatureException e) {
//	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//	        response.getWriter().write("Invalid JWT signature");
//	        response.getWriter().flush();
//	    } catch (IllegalArgumentException e) {
//	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//	        response.getWriter().write("JWT token compact of handler are invalid");
//	        response.getWriter().flush();
//	    } catch (Exception e) {
//	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//	        response.getWriter().write("An error occurred while processing the token");
//	        response.getWriter().flush();
//	    }
//	}
//	
//}