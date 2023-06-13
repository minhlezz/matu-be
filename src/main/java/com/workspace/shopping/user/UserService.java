package com.workspace.shopping.user;

import com.workspace.shopping.security.SecurityUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    public UserDTO getCredentials() {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        SecurityUser  user = (SecurityUser) principal;
        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        dto.setId(user.getId());
        return dto;
    }
}
