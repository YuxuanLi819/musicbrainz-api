package com.coles.artist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Service Not Available Exception
 */
@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE, reason = "Service Currently Unavailable, Please try Again Later!")
public class ServiceNotAvailableException extends Exception {

	private static final long serialVersionUID = -884860930639497715L;

	public ServiceNotAvailableException() {
		super();
	}

}
