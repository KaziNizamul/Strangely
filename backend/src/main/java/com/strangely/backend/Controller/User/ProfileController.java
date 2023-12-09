package com.strangely.backend.Controller.User;

import com.strangely.backend.Config.Exception.exceptionHandler;
import com.strangely.backend.Mapper.User.UserMP;
import com.strangely.backend.Model.User.DTOs.UserDTO;
import com.strangely.backend.Model.User.Entities.User;
import com.strangely.backend.Service.Auth.IUserService;
import com.strangely.backend.Service.Auth.IUserServiceJPA;
import com.strangely.backend.Service.Exception.Restexception;
import com.strangely.backend.Service.User.IUserProfile;
import com.strangely.backend.Service.User.Implementation.UserProfile;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@RestController
@RequestMapping("/strangely")
@CrossOrigin
public class ProfileController {
    @Autowired
    private IUserProfile userProfile;
    @Autowired
    private final IUserService userService;

    @Autowired
    private final IUserServiceJPA userServiceJPA;

    @Autowired
    private final exceptionHandler exceptionHandler;

    private final UserMP um;

    public ProfileController(UserProfile userProfile, IUserService userService, IUserServiceJPA userServiceJPA, exceptionHandler exceptionHandler, UserMP userMP) {
        this.userProfile = userProfile;
        this.userService = userService;
        this.userServiceJPA = userServiceJPA;
        this.exceptionHandler = exceptionHandler;
        this.um = userMP;
    }

    @GetMapping("/myaccount/{username}")
    public ResponseEntity<UserDTO> getUserProfile(@PathVariable String username) {
        ResponseEntity response = null;
        try {
            User u = userServiceJPA.findByUsernameU(username);// this is used to get userdata based on username
            UserDTO ud = null;
            if(u!= null)
            {
                ud = um.toUserDTO(u); // converting to userDTO
            }
            if (ud != null) {
                response = ResponseEntity.status(HttpStatus.OK).body(ud);
            } else {
                response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Not Found");
            }
        }catch (Restexception e)
        {
            exceptionHandler.handleException(e);
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
        return response;
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<UserDTO> updateUserProfile(@PathVariable String username, @RequestBody User u)
    {   ResponseEntity response = null;
        UserDTO output;
        try {
            output = userProfile.updateUserProfile(username, u);//update use profile data for any change in data
            response = ResponseEntity.status(HttpStatus.OK).body(output);
        } catch (Restexception e)
        {
            exceptionHandler.handleException(e);
        } catch (Exception e)
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }
        return response;
    }

    @GetMapping("/all_users")
    public ResponseEntity<List<UserDTO>> findAll() {

        ResponseEntity response = null;
        try {
            List<UserDTO> userDTOList = userService.findAll(); //find all users
            response = ResponseEntity.status(HttpStatus.OK).body(userDTOList);
        }catch (Restexception e)
        {
            exceptionHandler.handleException(e);
        }
        catch (Exception e)
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
        return response;
    }
}
