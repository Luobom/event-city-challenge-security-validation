package com.devsuperior.demo.dto;

import com.devsuperior.demo.annotations.validation.UserInsertValid;
import com.devsuperior.demo.entities.User;

import java.util.Set;
import java.util.stream.Collectors;

@UserInsertValid
public record UserInsertDTO(
        String name,
        String email,
        String password,
        Set<RoleDTO> roles
) {

    public UserInsertDTO(User entity) {
        this(entity.getName(), entity.getEmail(), entity.getPassword(), entity.getRoles().stream().map(RoleDTO::new).collect(Collectors.toSet()));
    }

}