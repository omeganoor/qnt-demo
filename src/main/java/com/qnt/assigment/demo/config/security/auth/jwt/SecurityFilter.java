//package com.qnt.assigment.demo.config.security;
//
//
//import com.qnt.assigment.demo.config.security.auth.TokenClaims;
//import com.qnt.assigment.demo.config.security.exception.InvalidTokenException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.util.AntPathMatcher;
//import org.springframework.util.PathMatcher;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Objects;
//
//@Configuration
//@Slf4j
//public class SecurityFilter extends OncePerRequestFilter {
//	private static final List<String> excludedUriPatterns = Arrays.asList("/swagger-ui*/**", "/v3/**", "/actuator/**");
//    private static final PathMatcher pathMatcher = new AntPathMatcher();
//    private final String secretKey = "J9bdyAZitT7RH3i8MVVfwdduC5BX1bNE";
//
//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) {
//        return excludedUriPatterns.stream()
//                .anyMatch(pattern -> pathMatcher.match(pattern, request.getServletPath()));
//    }
//
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws IOException, ServletException {
//
//        String authHeader = request.getHeader("Authorization");
//        if (Objects.isNull(authHeader) || authHeader.isEmpty()) {
//            throw new InvalidTokenException("Token required");
//        }
//
//        List<SimpleGrantedAuthority> roles = null;
//        if (authHeader.startsWith("Bearer ")) {
//            authHeader = authHeader.replaceFirst("Bearer ", "");
//
//            TokenClaims claims = oktaClient.validateToken(authHeader);
//            roles =  claims.getGroups().stream().map(group -> "ROLE_" + group)
//                .map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
//            RoleType roleType = getRoleType(claims.getGroups());
//            UserDetail userDetail = userClient.getDetailByToken(authHeader);
//
//            if (userDetail.getClientId() == null || userDetail.getClientId() <= 0) {
//                throw new InvalidTokenException("Client id not found");
//            }

//        }else {
//            if (!secretKey.equalsIgnoreCase(authHeader)) {
//                throw new InvalidTokenException("Token Not Valid");
//            }
//        }
//
//
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                null, null, roles);
//        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//
//        filterChain.doFilter(request, response);
//    }
//
//    private TokenClaims validateToken (String authHeader) {
//        return null;
//    }
//
//  private RoleType getRoleType(List<String> roles) {
//        boolean ops = false, admin = false;
//        for (String role: roles) {
//            if(role.contains("PRODUGIE_OPS")) {
//                ops = true;
//            } else if(role.contains("ADMIN")) {
//                admin = true;
//            }
//        }
//
//        if (ops) {
//            return RoleType.OPS;
//        } else if (admin) {
//            return RoleType.ADMIN;
//        } else {
//            return RoleType.USER;
//        }
//    }
//}