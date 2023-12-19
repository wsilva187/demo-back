package br.soc.avaliacao.security;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.soc.avaliacao.commons.exception.ForbiddenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;


@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private static final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer ";

    @Value("${jwt.secret}")
    private String secret;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            if (verificaTokenJWT(request)) {
                Claims claims = validaToken(request);
                if (claims.get("role") != null) {
                    configuraAutenticacaoSpring(claims);
                } else {
                    SecurityContextHolder.clearContext();
                }
                chain.doFilter(request, response);
            } else {
                chain.doFilter(request, response);
            }
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | ForbiddenException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
    }

    private Claims validaToken(HttpServletRequest request) {
        Objects.requireNonNull(secret, "Chave secreta não configurada");

        try {
            String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken).getBody();
        } catch (Exception e) {
            throw new ForbiddenException("Token inválido");
        }
    }

    private void configuraAutenticacaoSpring(Claims claims) {
        List<String> role = (List<String>) claims.get("role");

        String username = claims.get("login", String.class);

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
                role.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        SecurityContextHolder.getContext().setAuthentication(auth);

    }

    private boolean verificaTokenJWT(HttpServletRequest request) {
        String authenticationHeader = request.getHeader(HEADER);
        return authenticationHeader != null && authenticationHeader.startsWith(PREFIX);
    }

    public Object getUsuarioAtualizacao() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getPrincipal();
    }

}
