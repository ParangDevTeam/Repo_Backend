package com.vowing.parang.domain.slot.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 슬롯을 찾을 수 없음
 * */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class SlotNotFound extends RuntimeException {
}
