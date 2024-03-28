package com.vowing.parang.domain.log.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 로그 기록 없음
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class LogNotFound extends RuntimeException {}
