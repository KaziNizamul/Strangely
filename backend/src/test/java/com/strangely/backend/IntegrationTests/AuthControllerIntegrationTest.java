package com.strangely.backend.IntegrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.strangely.backend.Config.AuthConfig.AuthProvider;
import com.strangely.backend.Controller.Auth.AuthController;
import com.strangely.backend.Model.Auth.Dtos.JWTDTO;
import com.strangely.backend.Model.Auth.Dtos.LogoutDTO;
import com.strangely.backend.Model.Auth.Dtos.SignInDTO;
import com.strangely.backend.Model.Auth.Dtos.loginDetailsDTO;
import com.strangely.backend.Model.User.DTOs.UserDTO;
import com.strangely.backend.Service.User.IEmailService;
import com.strangely.backend.Service.Auth.IUserServiceJPA;
import com.strangely.backend.Service.Auth.Implementation.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthControllerIntegrationTest {

    private MockMvc mockMvc;

    @Mock
    private UserService us;

    @Mock
    private AuthProvider ap;

    @Mock
    private IEmailService es;

    @Mock
    private IUserServiceJPA usj;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    void testRegister() throws Exception {
        // Mock service response
        when(us.register(any(SignInDTO.class))).thenReturn(new UserDTO());

        // Create a sample SignInDTO
        SignInDTO signInDTO = new SignInDTO();

        // Perform the POST request
        mockMvc.perform(post("/registerSS")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(signInDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void testLogin() throws Exception {
        when(us.login(any(loginDetailsDTO.class))).thenReturn(new UserDTO());

        loginDetailsDTO loginDetailsDTO = new loginDetailsDTO("r1", "1234".toCharArray());

        mockMvc.perform(post("/loginSS")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(loginDetailsDTO)))
                .andExpect(status().isAccepted());
    }

    @Test
    void testLogout() throws Exception {
        LogoutDTO logoutDTO = new LogoutDTO();
        logoutDTO.setUserName("username");

        when(us.findByUsername(any(String.class))).thenReturn(new UserDTO());

        mockMvc.perform(post("/logoutSS")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(logoutDTO)))
                .andExpect(status().isAccepted());
    }


    @Test
    void testUserByToken() throws Exception {

        when(ap.getUserByJWT(any(String.class))).thenReturn(new UserDTO());

        JWTDTO jwtDTO = new JWTDTO("eyJhbGciOiJIUzI1NiIsIn");

        mockMvc.perform(post("/user-by-tokenSS")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(jwtDTO)))
                .andExpect(status().isAccepted());
    }

}
