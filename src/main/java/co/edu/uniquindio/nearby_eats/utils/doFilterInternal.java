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
import java.util.List;

@Component
@RequiredArgsConstructor
public class doFilterInternal extends OncePerRequestFilter {

    private  final JwtUtils jwtUtils;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, DELETE, OPTIONS");
        response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, Content-Type, Authorization");

        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {

            String requestURI = request.getRequestURI();
            List<String> userList = List.of("/api/user/update-account-user", "/api/user/delete-user/{id}", "/api/user/get-all-users", "/api/user/get-user/{id}");
            List<String> commentList = List.of("/api/comment/create-comment", "/api/comment/answer-comment", "/api/comment/delete-comment");
            List<String> userPlaceList = List.of("/api/place/create-place", "/api/place/update-place", "/api/place/delete-place", "/api/place/recommend-places/{token}", "/api/place/save/favorite/place", "/api/place/delete/favorite/place", "/api/place/get-place/by-status", "/api/place/get-place/by-category", "/api/place/get-place/by-location");
            List<String> imageList = List.of("/api/images/upload", "/api/images/delete", "/api/user/get-user/{id}",
                    "/api/place/get-place/by-user-id/{clientId}");
            List<String> modList = List.of("/api/place/review-place", "/api/user/get-all-users");

            String token = getToken(request);
            boolean error = true;

            try {
                if (userList.contains(requestURI)) {

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

                if (userPlaceList.contains(requestURI)) {

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

                if (commentList.contains(requestURI)) {

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

                if (imageList.contains(requestURI)) {

                    if (token != null) {
                        Jws<Claims> jws = jwtUtils.parseJwt(token);
                        if (!jws.getPayload().get("role").equals("CLIENT") || !jws.getPayload().get("role").equals("MODERATOR")) {
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

                if (modList.contains(requestURI)) {

                    if (token != null) {
                        Jws<Claims> jws = jwtUtils.parseJwt(token);
                        if (!jws.getPayload().get("role").equals("MODERATOR")) {
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
