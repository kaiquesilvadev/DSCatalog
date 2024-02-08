package com.example.DSCatalog.domain.dto.responce;

import java.util.HashSet;
import java.util.Set;

import com.example.DSCatalog.domain.dto.referencias.RoleRef;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponce {

	private Long id;
	private String FirstName;
	private String lastName;
	private String email;
	private Set<RoleRef> roles = new HashSet<>();
}
