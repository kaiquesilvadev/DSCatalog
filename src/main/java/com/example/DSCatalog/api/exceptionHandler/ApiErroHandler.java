package com.example.DSCatalog.api.exceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.DSCatalog.api.exceptionHandler.ApiErro.Field;
import com.example.DSCatalog.domain.exception.CategoryNaoEncontradaException;
import com.example.DSCatalog.domain.exception.EntidadeEmUsoException;
import com.example.DSCatalog.domain.exception.EntidadeNaoEncontradaException;
import com.example.DSCatalog.domain.exception.FormatoDeParametroInvalidoException;
import com.example.DSCatalog.domain.exception.ProductNaoEncontradoException;
import com.example.DSCatalog.domain.exception.UserNaoEncontradoException;

@ControllerAdvice
public class ApiErroHandler extends ResponseEntityExceptionHandler {
	

	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatusCode statusCode, WebRequest request) {
		String ErrorManager = ((HttpStatus) statusCode).getReasonPhrase();

		if (body == null)
			ApiErro.builder().status(statusCode.value()).erro(ErrorManager).build();

		if (body instanceof String)
			ApiErro.builder().status(statusCode.value()).erro((String) body).build();

		return super.handleExceptionInternal(ex, body, headers, statusCode, request);
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> trataEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, HttpHeaders headers,
			HttpStatusCode statusCode, WebRequest request) {

		ApiErro erro = ApiErro.builder().timestamp(OffsetDateTime.now()).status(HttpStatus.NOT_FOUND.value())
				.message(ex.getMessage()).erro(PathErro.ENTIDADE_NAO_ENCONTRADA.getTitle())
				.path(PathErro.ENTIDADE_NAO_ENCONTRADA.getUrl()).build();

		return handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.NOT_FOUND, request);

	}
	
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> trataEntidadeEmUsoException(EntidadeEmUsoException ex, HttpHeaders headers,
			HttpStatusCode statusCode, WebRequest request) {

		ApiErro erro = ApiErro.builder().timestamp(OffsetDateTime.now()).status(HttpStatus.CONFLICT.value())
				.message(ex.getMessage()).erro(PathErro.ENTIDADE_EM_USO.getTitle())
				.path(PathErro.ENTIDADE_EM_USO.getUrl()).build();

		return handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.CONFLICT, request);

	}

	@ExceptionHandler(CategoryNaoEncontradaException.class)
	public ResponseEntity<?> trataCategoryNaoEncontradaException(CategoryNaoEncontradaException ex, WebRequest request) {

		ApiErro erro = ApiErro.builder().timestamp(OffsetDateTime.now()).status(HttpStatus.NOT_FOUND.value())
				.message(ex.getMessage()).erro(PathErro.ENTIDADE_NAO_ENCONTRADA.getTitle())
				.path(PathErro.ENTIDADE_NAO_ENCONTRADA.getUrl()).build();

		return handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(ProductNaoEncontradoException.class)
	public ResponseEntity<?> trataProductNaoEncontradoException(ProductNaoEncontradoException ex, WebRequest request) {

		ApiErro erro = ApiErro.builder().timestamp(OffsetDateTime.now()).status(HttpStatus.NOT_FOUND.value())
				.message(ex.getMessage()).erro(PathErro.ENTIDADE_NAO_ENCONTRADA.getTitle())
				.path(PathErro.ENTIDADE_NAO_ENCONTRADA.getUrl()).build();

		return handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(UserNaoEncontradoException.class)
	public ResponseEntity<?> trataUserNaoEncontradoException(UserNaoEncontradoException ex, WebRequest request) {

		ApiErro erro = ApiErro.builder().timestamp(OffsetDateTime.now()).status(HttpStatus.NOT_FOUND.value())
				.message(ex.getMessage()).erro(PathErro.ENTIDADE_NAO_ENCONTRADA.getTitle())
				.path(PathErro.ENTIDADE_NAO_ENCONTRADA.getUrl()).build();

		return handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(FormatoDeParametroInvalidoException.class)
	public ResponseEntity<?> trataFormatoDeParametroInvalidoException(FormatoDeParametroInvalidoException ex, WebRequest request) {

		ApiErro erro = ApiErro.builder().timestamp(OffsetDateTime.now()).status(HttpStatus.NOT_FOUND.value())
				.message(ex.getMessage()).erro(PathErro.PARAMETRO_INVALIDO.getTitle())
				.path(PathErro.PARAMETRO_INVALIDO.getUrl()).build();

		return handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		List<Field> Filter = ex.getBindingResult().getFieldErrors().stream().map((x) -> {
			String messagem = messageSource.getMessage(x, LocaleContextHolder.getLocale());

			return ApiErro.Field.builder().nome(x.getField()).Message(messagem).build();

		}).collect(Collectors.toList());

		ApiErro erro = ApiErro.builder().path(PathErro.PARAMETRO_INVALIDO.getUrl()).timestamp(OffsetDateTime.now())
				.erro("Um ou mais campos estão inválidos").fields(Filter).build();

		return handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
	}

}
