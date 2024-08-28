package com.crio.rentread.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {

	private String firstName;
	private String lastName;

	@Id
	private String email;

	@Nonnull
	private String password;

}
