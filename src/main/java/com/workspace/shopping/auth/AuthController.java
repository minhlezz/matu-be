package com.workspace.shopping.auth;

import com.workspace.shopping.exception.UserExistException;
import com.workspace.shopping.role.RoleRepository;
import com.workspace.shopping.user.User;
import com.workspace.shopping.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor

public class AuthController {
    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

    private SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;
    private final RegistrationMapper registrationMapper;
    private final RoleRepository roleRepository;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void registerNewUserAccount(@RequestBody RegistrationDTO dto) {
        checkUserNameIsExisted(dto);

        User user = registrationMapper.toEntity(dto);

        Set roles = roleRepository.getDefaultRoles();

        user.setRoles(roles);

        userRepository.save(user);
    }

    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public void login(@RequestBody LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response ) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword()
                )
        );
        SecurityContext context = securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(authentication);
        securityContextHolderStrategy.setContext(context);
        securityContextRepository.saveContext(context, request, response);
    }

    private void checkUserNameIsExisted(RegistrationDTO dto) {
        boolean isUsernameExisted = userRepository.existsByUsername(dto.getUsername());

        if (isUsernameExisted) {
            throw new UserExistException(dto.getUsername() + " existed");
        }
    }
}
