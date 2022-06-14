package com.tweetapp.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import feign.FeignException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SampleFeignClientException extends FeignException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SampleFeignClientException(int status, String message) {
		super(status, message);
	}
}