package com.highfive.authservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.highfive.authservice.utils.CustomResponseEntity;
import com.highfive.authservice.utils.exception.DepartmentNotFoundException;
import com.highfive.authservice.utils.exception.UserNotFoundException;

@RestControllerAdvice
public class AuthExceptionController {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public CustomResponseEntity<List<String>> handleInvalidArgument(
			MethodArgumentNotValidException e) {

		List<String> invalidArguments = new ArrayList<>();

		e.getBindingResult().getFieldErrors().forEach(err -> {
			invalidArguments.add(err.getDefaultMessage());
		});

		return new CustomResponseEntity<>(invalidArguments, HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UserNotFoundException.class)
	public CustomResponseEntity<Object> handleUserNotFound(UserNotFoundException e) {

		return new CustomResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR,
				"USER NOT FOUND");
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(DepartmentNotFoundException.class)
	public CustomResponseEntity<Object> handleDepartmentNotFound(
			DepartmentNotFoundException e) {

		return new CustomResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR,
				"DEPARTMENT NOT FOUND");
	}

}
