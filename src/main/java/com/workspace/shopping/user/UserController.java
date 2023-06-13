package com.workspace.shopping.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor

@Transactional(readOnly = true)
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    @Transactional
    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/me")
    public UserDTO getCurrentUser() {
        return userService.getCredentials();
    }
}
