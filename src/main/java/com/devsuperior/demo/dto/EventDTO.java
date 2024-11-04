package com.devsuperior.demo.dto;

import java.time.LocalDate;

import com.devsuperior.demo.entities.Event;
import jakarta.validation.constraints.*;

public record EventDTO (
		Long id,

		@NotBlank( message = "Campo requerido")
		String name,

		@Future(message = "A data do evento não pode ser passada")
		LocalDate date,
		String url,

		@NotNull( message = "Campo requerido")
		Long cityId
){

	public EventDTO(Event entity) {
		this(entity.getId(), entity.getName(), entity.getDate(), entity.getUrl(), entity.getCity().getId());
	}

}
