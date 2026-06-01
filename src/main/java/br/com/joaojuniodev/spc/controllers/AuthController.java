package br.com.joaojuniodev.spc.controllers;

import br.com.joaojuniodev.spc.data.dtos.security.AccountCredentialsDTO;
import br.com.joaojuniodev.spc.data.dtos.security.CatechistCredentialsDTO;
import br.com.joaojuniodev.spc.data.dtos.security.RegisterUserDTO;
import br.com.joaojuniodev.spc.data.dtos.response.user.UserResponseDTO;
import br.com.joaojuniodev.spc.services.AuthService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/sign")
    public ResponseEntity<?> signIn(@RequestBody AccountCredentialsDTO credentials) {
        if (credentialsIsInvalid(credentials)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        var token = service.signIn(credentials);

        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        return ResponseEntity.ok(token);
    }

    @PostMapping("/catechist")
    public ResponseEntity<?> byCatechist(@RequestBody CatechistCredentialsDTO credentials) {
        if (credentialsCatechistIsInvalid(credentials)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        var token = service.signInCatechist(credentials);

        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        return ResponseEntity.ok(token);
    }

    @PutMapping("/refresh/{username}")
    public ResponseEntity<?> refreshToken(
        @PathVariable("username") String username,
        @RequestHeader("Authorization") String refreshToken
    ) {
        /*
        if (parametersAreInvalid(username, refreshToken)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        var token = service.refreshToken(username, refreshToken);
        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        return ResponseEntity.ok().body(token);
        */
        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody RegisterUserDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.register(dto));
    }

    boolean parametersAreInvalid(String username, String refreshToken) {
        return StringUtils.isBlank(username) || StringUtils.isBlank(refreshToken);
    }

    static boolean credentialsIsInvalid(AccountCredentialsDTO credentials) {
        return credentials == null ||
            StringUtils.isBlank(credentials.getUsername()) ||
            StringUtils.isBlank(credentials.getPassword());
    }

    static boolean credentialsCatechistIsInvalid(CatechistCredentialsDTO credentials) {
        return credentials == null || credentials.getCatechistId() == null || credentials.getCommunityOrParish() == null;
    }
}
