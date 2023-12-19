package br.soc.avaliacao.controller.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.soc.avaliacao.commons.exception.BadRequestException;
import br.soc.avaliacao.commons.exception.ClientException;
import br.soc.avaliacao.commons.exception.ForbiddenException;
import br.soc.avaliacao.commons.exception.NotAcceptableException;
import br.soc.avaliacao.commons.exception.NotFoundException;
import br.soc.avaliacao.commons.exception.NotFoundRuntimeException;
import br.soc.avaliacao.commons.exception.UnauthorizedException;
import br.soc.avaliacao.commons.exception.ValidacaoException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String ERRO_CAPTURADO = "Erro capturado pelo ExceptionHandler: {}";

	@ExceptionHandler(ClientException.class)
	public Object clientException(ClientException ex) {
		log.error(ERRO_CAPTURADO, ex.getMessage(), ex);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return ResponseEntity.status(ex.getStatus()).headers(headers).body(ex.getBody());
	}

	@ExceptionHandler({UnauthorizedException.class, BadCredentialsException.class})
	public ResponseEntity<Map<String, Object>> unauthorizedException(Exception ex) {
		log.error(ERRO_CAPTURADO, ex.getMessage(), ex);
		Map<String, Object> response = new HashMap<>();
		response.put("message",ex.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	}

	@ExceptionHandler(NotAcceptableException.class)
	public ResponseEntity<Map<String, Object>> notAcceptableException(NotAcceptableException ex) {
		log.error(ERRO_CAPTURADO, ex.getMessage(), ex);
		Map<String, Object> response = new HashMap<>();
		response.put("message",ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
	}

	@ExceptionHandler({ BadRequestException.class, ValidacaoException.class })
	public ResponseEntity<Map<String, Object>> badRequestException(Exception ex) {
		log.error(ERRO_CAPTURADO, ex.getMessage(), ex);
		Map<String, Object> response = new HashMap<>();
		response.put("message",ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler({ForbiddenException.class, AccessDeniedException.class})
	public ResponseEntity<Map<String, Object>> forbiddenException(Exception ex) {
		log.error(ERRO_CAPTURADO, ex.getMessage(), ex);
		Map<String, Object> response = new HashMap<>();
		response.put("message", ex.getMessage());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
	}

	@ExceptionHandler({NotFoundException.class, NotFoundRuntimeException.class})
	public ResponseEntity<Map<String, Object>> notFoundException(Exception ex) {
		log.error(ERRO_CAPTURADO, ex.getMessage(), ex);
		Map<String, Object> response = new HashMap<>();
		response.put("message",ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@ExceptionHandler({Exception.class})
	public ResponseEntity<Map<String, Object>> erroInterno(Exception ex) {
		log.error(ERRO_CAPTURADO, ex.getMessage(), ex);
		Map<String, Object> response = new HashMap<>();
		response.put("message",ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
}