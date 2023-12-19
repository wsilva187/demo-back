package br.soc.avaliacao.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import br.soc.avaliacao.services.TokenContext;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {

	private final TokenContext tokenContext;

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest req = (HttpServletRequest) request;
		final String token = req.getHeader("Authorization");
		tokenContext.setToken(token);
		chain.doFilter(request, response);
	}

}

