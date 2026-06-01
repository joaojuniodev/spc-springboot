package br.com.joaojuniodev.spc.controllers;

import br.com.joaojuniodev.spc.data.dtos.security.AdminCreateUserDTO;
import br.com.joaojuniodev.spc.data.dtos.response.user.UserResponseDTO;
import br.com.joaojuniodev.spc.services.AuthService;
import br.com.joaojuniodev.spc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserService service;

    @Autowired
    private AuthService authService;

    @PostMapping
    @PreAuthorize("hasAuthority('USER_CREATE')")
    public ResponseEntity<UserResponseDTO> createByAdmin(@RequestBody AdminCreateUserDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.createByAdmin(dto));
    }
}