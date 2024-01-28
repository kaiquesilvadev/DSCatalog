package com.example.DSCatalog.domain.dto.responce;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponce {

	private Long id;
	private String FirstName;
	private String lastName;
	private String email;
	private String password;
}
