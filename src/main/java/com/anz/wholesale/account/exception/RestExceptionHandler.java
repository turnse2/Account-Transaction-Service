package com.anz.wholesale.account.exception;

import org.hibernate.QueryTimeoutException;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
/**
 * ExceptionHandler class provide a unified way to handle rest controller exceptions.
 * @author Dennis Hu
 * @since 2019-10-13
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Malformed JSON request";
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
	}

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Malformed JSON request";
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
	}
	

	
	@ExceptionHandler(QueryTimeoutException.class)
	protected ResponseEntity<Object> handleQueryTimeoutException(QueryTimeoutException ex) {
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
		apiError.setMessage(ex.getMessage());
		apiError.setDebugMessage(ex.getStackTrace() != null ? Arrays.toString(ex.getStackTrace()) : ex.getMessage());
		apiError.setDetailedStatus("5001");
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(JDBCConnectionException.class)
	protected ResponseEntity<Object> handleJDBCConnectionException(JDBCConnectionException ex) {
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
		apiError.setMessage(ex.getMessage());
		apiError.setDebugMessage(ex.getStackTrace() != null ? Arrays.toString(ex.getStackTrace()) : ex.getMessage());
		apiError.setDetailedStatus("5001");
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(AccountNotFoundException.class)
	protected ResponseEntity<Object> handleAccountNotFound(AccountNotFoundException ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
		apiError.setMessage(ex.getMessage());
		apiError.setDebugMessage(ex.getStackTrace() != null ? Arrays.toString(ex.getStackTrace()) : ex.getMessage());
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(UserNotFoundException.class)
	protected ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
		apiError.setMessage(ex.getMessage());
		apiError.setDebugMessage(ex.getStackTrace() != null ? Arrays.toString(ex.getStackTrace()) : ex.getMessage());
		return buildResponseEntity(apiError);
	}

}