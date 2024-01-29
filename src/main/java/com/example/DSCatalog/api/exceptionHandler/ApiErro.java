package com.example.DSCatalog.api.exceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

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
	 private List<Field> fields = new ArrayList<>();
	 
	 @JsonInclude(JsonInclude.Include.NON_NULL)
		@Getter
		@Builder
		public static class Field{
			private String nome;
			private String Message;
		}
}
