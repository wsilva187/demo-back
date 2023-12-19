package br.soc.avaliacao.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.soc.avaliacao.dto.JwtResponseDTO;
import br.soc.avaliacao.services.TokenService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/authenticate")
public class AuthController {

    private final TokenService jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @PostMapping
    public JwtResponseDTO createAuthenticationToken(@RequestHeader("Authorization") String authorizationHeader) throws Exception {

        // Extrai as credenciais do cabeçalho de autorização
        String[] credentials = extractCredentials(authorizationHeader);

        // Realiza a autenticação
        authenticate(credentials[0], credentials[1]);

        // Gera o token JWT
        UserDetails userDetails = userDetailsService.loadUserByUsername(credentials[0]);
        String token = jwtTokenUtil.generateToken(userDetails);

        return JwtResponseDTO.builder()
                .token(token)
                .build();
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
    }

    private String[] extractCredentials(String authorizationHeader) {
        // Remove o prefixo 'Basic ' e decodifica as credenciais base64
        String base64Credentials = authorizationHeader.substring("Basic ".length()).trim();
        byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
        String decodedString = new String(decodedBytes, StandardCharsets.UTF_8);

        // Divide as credenciais em um array [username, password]
        return decodedString.split(":", 2);
    }
}
