
package com.ms.bugzilla.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ms.bugzilla.service.CustomUserDetailsService;

@Component
public class JwtTokenAuthorizationOncePerRequestFilter extends OncePerRequestFilter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/*
	 * @Autowired private UserDetailsService jwtInMemoryUserDetailsService;
	 */
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Value("${jwt.http.request.header}")
	private String tokenHeader;

	/*
	 * @Override protected void doFilterInternal(HttpServletRequest request,
	 * HttpServletResponse response, FilterChain chain) throws ServletException,
	 * IOException { logger.debug("Authentication Request For '{}'",
	 * request.getRequestURL());
	 * 
	 * final String requestTokenHeader = request.getHeader(this.tokenHeader);
	 * logger.debug("requestTokenHeader  '{}'", requestTokenHeader); String username
	 * = null; String jwtToken = null; if (requestTokenHeader != null &&
	 * requestTokenHeader.startsWith("Bearer ")) { jwtToken =
	 * requestTokenHeader.substring(7); try { username =
	 * jwtTokenUtil.getUsernameFromToken(jwtToken); } catch
	 * (IllegalArgumentException e) {
	 * logger.error("JWT_TOKEN_UNABLE_TO_GET_USERNAME", e); } catch
	 * (ExpiredJwtException e) { logger.warn("JWT_TOKEN_EXPIRED", e); } } else {
	 * logger.warn("JWT_TOKEN_DOES_NOT_START_WITH_BEARER_STRING"); }
	 * 
	 * logger.debug("JWT_TOKEN_USERNAME_VALUE '{}'", username); // if (username !=
	 * null && SecurityContextHolder.getContext().getAuthentication() == null) { if
	 * (jwtToken != null && jwtTokenUtil.validateToken(jwtToken)) {
	 * 
	 * UserDetails userDetails =
	 * this.customUserDetailsService.loadUserByUsername(username);
	 * 
	 * if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
	 * UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
	 * UsernamePasswordAuthenticationToken( userDetails, null,
	 * userDetails.getAuthorities()); usernamePasswordAuthenticationToken
	 * .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	 * SecurityContextHolder.getContext().setAuthentication(
	 * usernamePasswordAuthenticationToken); } }
	 * 
	 * chain.doFilter(request, response); }
	 */
	
	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);

            if (StringUtils.hasText(jwt) && jwtTokenUtil.validateToken(jwt)) {
                Long userId = jwtTokenUtil.getUserIdFromJWT(jwt);

                /*
                    Note that you could also encode the user's username and roles inside JWT claims
                    and create the UserDetails object by parsing those claims from the JWT.
                    That would avoid the following database hit. It's completely up to you.
                 */
                UserDetails userDetails = customUserDetailsService.loadUserById(userId);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }

        filterChain.doFilter(request, response);
    }
	  private String getJwtFromRequest(HttpServletRequest request) {
	        String bearerToken = request.getHeader("Authorization");
	        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
	            return bearerToken.substring(7, bearerToken.length());
	        }
	        return null;
	    }
}
