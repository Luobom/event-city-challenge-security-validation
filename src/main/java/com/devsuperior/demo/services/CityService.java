package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.repositories.CityRepository;

import com.devsuperior.demo.services.exceptions.DatabaseException;
import com.devsuperior.demo.services.exceptions.ResourceNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CityService {

    private final CityRepository repository;

    public CityService(CityRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<CityDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(CityDTO::new);
    }

    @Transactional
    public CityDTO insert(CityDTO dto) {
        City entity = new City();
        BeanUtils.copyProperties(dto, entity);
        return new CityDTO(repository.save(entity));
    }


    @Transactional
    public void delete(Long id) {
        try {
            if (!repository.existsById(id)) {
                throw new ResourceNotFoundException("City not found");
            }
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation - This city has dependencies and cannot be deleted.");
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("City not found for the given ID: " + id);
        }
    }

}
