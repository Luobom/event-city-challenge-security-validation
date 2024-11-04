package com.devsuperior.demo.dto;

import com.devsuperior.demo.entities.City;
import jakarta.validation.constraints.NotBlank;

public record CityDTO (
		Long id,

		@NotBlank( message = "Campo requerido")
		String name
){

	public CityDTO(City entity) {
		this(entity.getId(), entity.getName());
	}

}
