package com.fluxocaixa;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderTest {

    @Test
    public void passwordEncodedSuccessTest() {
        // Cria uma instância do BCryptPasswordEncoder
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Define a senha a ser codificada
        String password = "password";

        // Codifica a senha
        String encodedPassword = passwordEncoder.encode(password);

        // Verifica se a senha fornecida corresponde à senha codificada
        boolean matches = passwordEncoder.matches(password, encodedPassword);

        Assert.assertTrue(matches);

    }

}
