package com.strangely.backend.Controller.Auth;

import com.strangely.backend.Config.AuthConfig.AuthProvider;
import com.strangely.backend.Model.Auth.Dtos.*;
import com.strangely.backend.Model.User.DTOs.UserDTO;
import com.strangely.backend.Service.User.IEmailService;
import com.strangely.backend.Service.Auth.IUserService;
import com.strangely.backend.Service.Exception.Restexception;
import com.strangely.backend.Config.Exception.exceptionHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.net.URI;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@CrossOrigin
public class AuthController {

    private final IUserService userService;
    private final AuthProvider authProvider;
    private final IEmailService emailService;

    @Autowired
    private final exceptionHandler exceptionHandler;

    // This method is a POST mapping for "/registerSS", designed to register a new user.
// The CrossOrigin annotation is used to handle Cross-Origin Resource Sharing (CORS) for this endpoint.
    @PostMapping(value = "/registerSS", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<UserDTO> register(@RequestBody @Valid SignInDTO signInData) {
        // Initialize the response entity to be populated based on the outcome of the operation.
        ResponseEntity response = null;

        try {
            // Set the joining date for the user to the current date and time.
            signInData.setJoiningdate(LocalDateTime.now());

            // Register the user using the provided SignInDTO through the userService.
            UserDTO register = userService.register(signInData);

            // Set a token for the registered user using the authentication provider.
            register.setToken(authProvider.createToken(register));

            // Print the registered user information to the console.
            System.out.println(register);

            // Create a URI for the newly registered user's account.
            ResponseEntity.created(URI.create("/strangely/myaccount/" + register.getUserName()));

            // Create a 201 CREATED response with the registered user information in the response body.
            response = ResponseEntity.status(HttpStatus.CREATED).body(register);
        } catch (Restexception e) {
            // If a Restexception is caught during the operation:
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionHandler.handleException(e));
        }
        catch (Exception e)
        {
            // Generate a 400 BAD REQUEST response with the exception details in the response body.
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }

        return response;
    }


    // This method is a POST mapping for "/loginSS", designed to log in a user.
    // The CrossOrigin annotation is used to handle Cross-Origin Resource Sharing (CORS) for this endpoint.
    @PostMapping(value = "/loginSS", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<UserDTO> login(@RequestBody @Valid loginDetailsDTO loginDetails) {
        // Initialize the response entity to be populated based on the outcome of the operation.
        ResponseEntity response = null;

        try {
            // Log in the user using the provided loginDetailsDTO through the userService.
            UserDTO login = userService.login(loginDetails);

            // Set a token for the logged-in user using the authentication provider.
            login.setToken(authProvider.createToken(login));

            // Print the logged-in user information to the console.
            System.out.println(login);

            // Create a 202 ACCEPTED response with the logged-in user information in the response body.
            response = ResponseEntity.status(HttpStatus.ACCEPTED).body(login);
        } catch (Restexception e) {
            // If a Restexception is caught during the operation:
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionHandler.handleException(e));
        }
        catch (Exception e)
        {
            // Generate a 400 BAD REQUEST response with the exception details in the response body.
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
        return response;
    }


    // This method is a POST mapping for "/logoutSS", designed to log out a user.
// The CrossOrigin annotation is used to handle Cross-Origin Resource Sharing (CORS) for this endpoint.
    @PostMapping(value = "/logoutSS", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<UserDTO> logout(@RequestBody @Valid LogoutDTO logoutData) {
        // Initialize the response entity to be populated based on the outcome of the operation.
        ResponseEntity response = null;

        // Find the user by username using the userService.
        UserDTO userDTO = userService.findByUsername(logoutData.getUserName());

        try {
            // Check if the userDTO is not null.
            if (userDTO != null) {
                // Clear the token for the logged-out user.
                userDTO.setToken(null);

                // Create a 202 ACCEPTED response with the logged-out user information in the response body.
                response = ResponseEntity.status(HttpStatus.ACCEPTED).body(userDTO);
            } else {
                // If the user is not found, generate a 400 BAD REQUEST response with a null body.
                response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        } catch (Restexception e) {
            // If a Restexception is caught during the operation:
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionHandler.handleException(e));
        } catch (Exception e) {
            // If a generic Exception is caught during the operation:

            // Generate a 400 BAD REQUEST response with the exception details in the response body.
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }
        return response;
    }


    // This method is a POST mapping for "/user-by-tokenSS", designed to retrieve a user based on a provided JWT token.
// The CrossOrigin annotation is used to handle Cross-Origin Resource Sharing (CORS) for this endpoint.
    @PostMapping(value = "/user-by-tokenSS", consumes = MediaType.ALL_VALUE)
    @CrossOrigin
    public ResponseEntity<UserDTO> userByToken(@RequestBody @Valid JWTDTO jwt) {
        // Initialize the response entity to be populated based on the outcome of the operation.
        ResponseEntity response = null;

        try {
            // Retrieve the user information using the provided JWT token through the authProvider.
            UserDTO ud = authProvider.getUserByJWT(jwt.JWT());

            // Print the user information to the console.
            System.out.println(ud);

            // Set the token for the retrieved user using the JWT token from the request.
            ud.setToken(jwt.JWT());

            // Create a 202 ACCEPTED response with the retrieved user information in the response body.
            response = ResponseEntity.status(HttpStatus.ACCEPTED).body(ud);
        }catch (Restexception e) {
            // If a Restexception is caught during the operation:
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionHandler.handleException(e));
        } catch (Exception e) {
            // If a generic Exception is caught during the operation:

            // Generate a 400 BAD REQUEST response with the exception details in the response body.
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }        return response;
    }

    @PostMapping("/resetpasswordSS")
    @CrossOrigin
    public ResponseEntity<RequestDTO> resetPassword(@RequestBody @Valid NewPass newPassword)
    {
        ResponseEntity response = null;
        try {
            //reset password is required if user ever forgets the password
            UserDTO userData = userService.findByResetToken(newPassword.getResetToken());
            if (userData != null) {
                ResetPasswordDTO rp = new ResetPasswordDTO(userData.getUserID(), userData.getEmail(), userData.getPhone_number(), userData.getFirstName(), userData.getLastName(), userData.getJoiningDate(), userData.getAreaId(), userData.getUserName(), newPassword.getNewPassword().toCharArray(),null);
                userService.reRegister(rp);
                response = ResponseEntity.status(HttpStatus.OK).body((new RequestDTO("Success(Status Code: 204","Password reset successful")));
            }
            else{
                response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestDTO("Error404","Sorry user not found"));
            }
        }catch (Restexception e) {
            // If a Restexception is caught during the operation:
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionHandler.handleException(e));
        } catch (Exception e) {
            // If a generic Exception is caught during the operation:

            // Generate a 400 BAD REQUEST response with the exception details in the response body.
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }
        return response;
    }

    @PostMapping("/resetSS")
    @CrossOrigin
    public ResponseEntity<RequestDTO> reset(@RequestBody @Valid Reset resetData){
          UserDTO  userData = userService.findByUsername(resetData.getUsername());
          ResponseEntity response = null;
          try {
              //sending the reset token to the user
              if(userData != null) {
                  userData.setResetToken(UUID.randomUUID().toString());
                  // Save token to database so that it matches when resetting password
                  userService.Insert(userData);
                  // Email message for reseting password
                  SimpleMailMessage passwordReset = new SimpleMailMessage();
                  passwordReset.setTo(userData.getEmail());
                  passwordReset.setFrom("rachitgrevq@gmail.com");
                  passwordReset.setSubject("Your password reset request");
                  String link =  "http://172.17.1.123:8074" + "/resetpasswordSS?resetToken=" + userData.getResetToken();
                  passwordReset.setText("To reset your password, click the on link below:\n" + link);
                  emailService.sendEmail(passwordReset);
                  String message= "A password reset link is shared with  "+ userData.getEmail();
                  response = ResponseEntity.status(HttpStatus.OK).body(new RequestDTO("Success(Code:200)", message));
              }
              else {
                  response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestDTO("Error", "Sorry user not found"));
              }
          }catch (Restexception e) {
              // If a Restexception is caught during the operation:
              response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionHandler.handleException(e));
          } catch (Exception e) {
              // If a generic Exception is caught during the operation:

              // Generate a 400 BAD REQUEST response with the exception details in the response body.
              response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
          }
          return  response;
    }
}
