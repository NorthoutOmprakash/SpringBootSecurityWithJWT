package com.devglan.config;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

//	@ExceptionHandler(RecordNotFoundException.class)
//	public final ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex, WebRequest request) {
//		List<String> details = new ArrayList<>();
//		details.add(ex.getLocalizedMessage());
//		ErrorResponse error = new ErrorResponse("Record Not Found", details);
//		return new ResponseEntity(error, HttpStatus.NOT_FOUND);
//	}
//
//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		List<String> details = new ArrayList<>();
//		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
//			details.add(error.getDefaultMessage());
//		}
//		ErrorResponse error = new ErrorResponse("Validation Failed", details);
//		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
//	}
}
