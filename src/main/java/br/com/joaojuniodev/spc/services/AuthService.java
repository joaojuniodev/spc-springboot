package br.com.joaojuniodev.spc.services;

import br.com.joaojuniodev.spc.data.dtos.response.user.UserResponseDTO;
import br.com.joaojuniodev.spc.data.dtos.security.*;
import br.com.joaojuniodev.spc.exceptions.NotFoundException;
import br.com.joaojuniodev.spc.exceptions.ObjectIsNullException;
import br.com.joaojuniodev.spc.infrastructure.security.JwtTokenProvider;
import br.com.joaojuniodev.spc.models.Catechist;
import br.com.joaojuniodev.spc.models.Role;
import br.com.joaojuniodev.spc.models.User;
import br.com.joaojuniodev.spc.repositories.CatechistRepository;
import br.com.joaojuniodev.spc.repositories.RoleRepository;
import br.com.joaojuniodev.spc.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private final Logger logger = LoggerFactory.getLogger(AuthService.class.getName());

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CatechistRepository catechistRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public TokenDTO signIn(AccountCredentialsDTO credentials) {
        logger.info("Performing the login");

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                credentials.getUsername(),
                credentials.getPassword()
            )
        );

        var user = repository.findByUserName(credentials.getUsername());
        if (user == null) {
            throw new UsernameNotFoundException("Username " + credentials.getUsername() + " not found!");
        }

        var roles = user.getRoles()
            .stream()
            .map(Role::getAuthority)
            .toList();

        String fullName = repository.findFullNameByUser(credentials.getUsername());

        return tokenProvider.createAccessToken(
            credentials.getUsername(),
            fullName,
            roles
        );
    }

    public TokenDTO signInCatechist(CatechistCredentialsDTO credentials) {
        logger.info("Performing the Login by Catechist");

        String username = null;

        Catechist catechist = catechistRepository.findAll()
            .stream()
            .filter(c -> isValidCatechist(credentials, c))
            .findFirst()
            .orElseThrow(() -> new UsernameNotFoundException("Catechist not found!"));

        var user = catechist.getUser();
        if (user == null) {
            throw new UsernameNotFoundException("Catechist has no linked user!");
        }

        var roles = user.getRoles()
            .stream()
            .map(Role::getAuthority)
            .toList();

        return tokenProvider.createCatechistAccessToken(
            user.getUsername(),
            user.getFullName(),
            catechist.getNameCommunityOrParish(),
            roles
        );
    }

    private static boolean isValidCatechist(CatechistCredentialsDTO credentials, Catechist catechist) {
        return catechist.getId().equals(credentials.getCatechistId()) && catechist.getNameCommunityOrParish().equals(credentials.getCommunityOrParish());
    }

    public ResponseEntity<TokenDTO> refreshToken(String username, String fulLName, String refreshToken) {
        var user = repository.findByUserName(username);
        TokenDTO token;
        if (user != null) {
            token = tokenProvider.refreshToken(refreshToken, fulLName);
        } else {
            throw new ObjectIsNullException();
        }

        return ResponseEntity.ok(token);
    }

    public UserResponseDTO register(RegisterUserDTO dto) {
        Role defaultRole = roleRepository.findByName("ROLE_CATECHIST")
            .orElseThrow(() -> new NotFoundException("Role : 'ROLE_CATECHIST'" + " not found!"));

        User user = buildUser(dto.getUsername(), dto.getPassword(), dto.getFullName());
        user.setRoles(List.of(defaultRole));

        User savedUser = repository.save(user);

        return toResponse(savedUser);
    }

    public UserResponseDTO createByAdmin(AdminCreateUserDTO dto) {
        List<Role> authorities = roleRepository.findAllById(dto.getRoleIds());

        User user = buildUser(dto.getUsername(), dto.getPassword(), dto.getFullName());
        user.setRoles(authorities);

        User savedUser = repository.save(user);

        return toResponse(savedUser);
    }

    private UserResponseDTO toResponse(User user) {
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getPassword(), user.getEnabled());
    }

    private User buildUser(String username, String password, String fullName) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(generatedPassword(password));
        user.setFullName(fullName);
        user.setEnabled(true);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        return user;
    }

    public AccountCredentialsDTO create(AccountCredentialsDTO user) {

        User entity = new User();
        entity.setUsername(user.getUsername());
        entity.setPassword(generatedPassword(user.getPassword()));
        entity.setFullName(user.getFullName());
        entity.setEnabled(true);
        entity.setAccountNonLocked(true);
        entity.setAccountNonExpired(true);
        entity.setCredentialsNonExpired(true);

        var dto = repository.save(entity);
        return new AccountCredentialsDTO(dto.getUsername(), dto.getPassword(),dto.getFullName());
    }

    private String generatedPassword(String passwordString) {
        return passwordEncoder.encode(passwordString);
    }
}
