package com.devsuperior.demo.dto;

import com.devsuperior.demo.entities.City;

public record CityDTO (
		Long id,
		String name
){

	public CityDTO(City entity) {
		this(entity.getId(), entity.getName());
	}

}
