package com.devsuperior.demo.dto;

import com.devsuperior.demo.annotations.validation.UserUpdateValid;
import com.devsuperior.demo.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;
import java.util.stream.Collectors;

@UserUpdateValid
public record UserDTO(
        Long id,

        @NotEmpty(message = "Campo obrigatório")
        String name,

        @Email(message = "Favor entrar um email válido")
        String email,
        Set<RoleDTO> roles

) {

    public UserDTO(User entity) {
        this(entity.getId(), entity.getName(), entity.getEmail(), entity.getRoles().stream().map(RoleDTO::new).collect(Collectors.toSet()));
    }

}
