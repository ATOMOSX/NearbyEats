package co.edu.uniquindio.nearby_eats.utils;

import co.edu.uniquindio.nearby_eats.dto.MessageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.SignatureException;

@Component
@RequiredArgsConstructor
public class doFilterInternal extends OncePerRequestFilter {

    private  final JwtUtils jwtUtils;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, Content-Type, Authorization");

        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {

            String requestURI = request.getRequestURI();

            String token = getToken(request);
            boolean error = true;

            try {
                if (requestURI.startsWith("/api/user")) {

                    if (token != null) {
                        Jws<Claims> jws = jwtUtils.parseJwt(token);
                        if (!jws.getPayload().get("role").equals("CLIENT")) {
                            createErrorResponse("You do not have permission to access this resource",
                                    HttpServletResponse.SC_FORBIDDEN, response);
                        } else {
                            error = false;
                        }
                    } else {
                        createErrorResponse("You do not have permission to access this resource",
                                HttpServletResponse.SC_FORBIDDEN, response);
                    }
                } else {
                    error = false;
                }

                if (requestURI.startsWith("/api/place")) {

                    if (token != null) {
                        Jws<Claims> jws = jwtUtils.parseJwt(token);
                        if (!jws.getPayload().get("role").equals("CLIENT")) {
                            createErrorResponse("You do not have permission to access this resource",
                                    HttpServletResponse.SC_FORBIDDEN, response);
                        } else {
                            error = false;
                        }
                    } else {
                        createErrorResponse("You do not have permission to access this resource",
                                HttpServletResponse.SC_FORBIDDEN, response);
                    }
                } else {
                    error = false;
                }

                if (requestURI.startsWith("/api/comment")) {

                    if (token != null) {
                        Jws<Claims> jws = jwtUtils.parseJwt(token);
                        if (!jws.getPayload().get("role").equals("CLIENT")) {
                            createErrorResponse("You do not have permission to access this resource",
                                    HttpServletResponse.SC_FORBIDDEN, response);
                        } else {
                            error = false;
                        }
                    } else {
                        createErrorResponse("You do not have permission to access this resource",
                                HttpServletResponse.SC_FORBIDDEN, response);
                    }
                } else {
                    error = false;
                }
            } catch (MalformedJwtException e) {
                createErrorResponse("The Token is incorrect",
                        HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response);

            } catch (ExpiredJwtException e) {
                createErrorResponse("The Token is expired",
                        HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response);

            } catch (Exception e) {
                createErrorResponse(e.getMessage(),
                        HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response);

            }

            if (!error) {
                filterChain.doFilter(request, response);
            }

        }
    }

    private String getToken(HttpServletRequest req) {
        String header = req.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer "))
            return header.replace("Bearer ", "");
        return null;
    }

    private void createErrorResponse(String message, int erroCode, HttpServletResponse
            response) throws IOException {
        MessageDTO<String> dto = new MessageDTO<>(true, message);
        response.setContentType("application/json");
        response.setStatus(erroCode);
        response.getWriter().write(new ObjectMapper().writeValueAsString(dto));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
