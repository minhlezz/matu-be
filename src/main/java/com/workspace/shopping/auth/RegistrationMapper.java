package com.workspace.shopping.auth;


import com.workspace.shopping.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class RegistrationMapper {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Mapping(
            source = "dto.password",
            target = "password",
            qualifiedByName = "encodePassword"
    )
    abstract User toEntity(RegistrationDTO dto);

    @Named("encodePassword")
    String encode(String password) {
        return  passwordEncoder.encode(password);
    }
}
