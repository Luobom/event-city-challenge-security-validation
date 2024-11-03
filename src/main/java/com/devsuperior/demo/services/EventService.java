package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.EventRepository;
import com.devsuperior.demo.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public EventDTO update(Long id, EventDTO dto) {
        try {
            Event entity = repository.getReferenceById(id);
            String[] ignoredProperties = {"id", "cityId"};
            BeanUtils.copyProperties(dto, entity, ignoredProperties);
            entity.getCity().setId(dto.cityId());
            return new EventDTO(repository.save(entity));
        } catch (EntityNotFoundException | FatalBeanException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    @Transactional
    public EventDTO insert(EventDTO dto) {
        Event entity = new Event();
        BeanUtils.copyProperties(dto, entity);
        entity.setCity(new City(dto.cityId(), null));
        return new EventDTO(repository.save(entity));
    }


}