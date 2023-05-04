package com.highfive.authservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.highfive.authservice.utils.exception.DepartmentNotFoundException;
import com.highfive.authservice.utils.exception.UserAlreadyExistsException;
import com.highfive.authservice.utils.exception.UserNotFoundException;

@RestControllerAdvice
public class AuthExceptionController {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<String>> handleInvalidArgument(
			MethodArgumentNotValidException e) {

		List<String> invalidArguments = new ArrayList<>();

		e.getBindingResult().getFieldErrors().forEach(err -> {
			invalidArguments.add(err.getDefaultMessage());
		});

		return new ResponseEntity<>(invalidArguments, HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {

		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<String> handleUserAlreadyExists(UserAlreadyExistsException e) {

		return new ResponseEntity<>("USER_ID ALREADY EXISTS", HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFound(UserNotFoundException e) {

		return new ResponseEntity<>("USER NOT FOUND", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(DepartmentNotFoundException.class)
	public ResponseEntity<Object> handleDepartmentNotFound(
			DepartmentNotFoundException e) {

		return new ResponseEntity<>("DEPARTMENT NOT FOUND",
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
