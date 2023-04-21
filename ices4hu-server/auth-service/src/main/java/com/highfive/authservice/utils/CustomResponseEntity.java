package com.highfive.authservice.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CustomResponseEntity<T> extends ResponseEntity<Map<String, Object>> {

	public CustomResponseEntity(T body, HttpStatus status) {
		super(customize(body, status, status.toString()), status);
	}

	public CustomResponseEntity(T body, HttpStatus status, String message) {
		super(customize(body, status, message), status);

	}

	private static Map<String, Object> customize(Object body, HttpStatus status, String message) {
		Map<String, Object> map = new HashMap<>();
		map.put("data", body);
		map.put("message", message);
		map.put("status", status);
		if (body instanceof Collection<?> && status == HttpStatus.OK)
			map.put("length", ((Collection<?>) body).size());

		return map;
	}

}
