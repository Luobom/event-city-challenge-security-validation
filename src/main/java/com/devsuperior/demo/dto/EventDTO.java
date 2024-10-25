package com.devsuperior.demo.dto;

import java.time.LocalDate;

import com.devsuperior.demo.entities.Event;

public record EventDTO (
		Long id,
		String name,
		LocalDate date,
		String url,
		Long cityId
){

	public EventDTO(Event entity) {
		this(entity.getId(), entity.getName(), entity.getDate(), entity.getUrl(), entity.getCity().getId());
	}

}
