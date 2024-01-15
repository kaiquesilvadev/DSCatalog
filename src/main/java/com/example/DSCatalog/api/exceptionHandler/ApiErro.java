package com.example.DSCatalog.api.exceptionHandler;

import java.time.OffsetDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiErro {

	 private OffsetDateTime timestamp;
	 private Integer status;
	 private String erro;
	 private String message;
	 private String path;
}
