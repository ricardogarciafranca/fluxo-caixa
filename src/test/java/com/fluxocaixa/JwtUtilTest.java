package com.fluxocaixa;

import com.fluxocaixa.entities.Usuario;
import com.fluxocaixa.util.JwtUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = FluxoCaixaApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class JwtUtilTest {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void createTokenAndValidTokenSuccessTest() {
        Usuario usuario = new Usuario();
        usuario.setId("1");
        String token = jwtUtil.createToken(usuario);
        Assert.assertNotNull(jwtUtil.validar(token));
    }

}