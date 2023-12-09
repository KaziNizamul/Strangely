package com.strangely.backend.Service.User.Implementation;

import com.strangely.backend.Model.User.DTOs.UserDTO;
import com.strangely.backend.Model.User.Entities.User;
import com.strangely.backend.Repository.User.UserRepository;
import com.strangely.backend.Service.Auth.IUserServiceJPA;
import com.strangely.backend.Service.User.IUserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.strangely.backend.Mapper.User.UserMP;
@RequiredArgsConstructor
@Service
public class UserProfile implements IUserProfile {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IUserServiceJPA usj;


    // Update the user profile based on the provided information.
    @Override
    public UserDTO updateUserProfile(String username, User user) {
        User updatedUser = usj.findByUsernameU(username);

        // Check if the user with the provided username exists.
        if(updatedUser == null) {
            return null;
        }

        // Check and update each attribute of the user if a non-null value is provided.
        if(user.getPhone_number() != null) {
            updatedUser.setPhone_number(user.getPhone_number());
        }
        if(user.getEmail() != null) {
            updatedUser.setEmail(user.getEmail());
        }
        if(user.getFirst_name() != null) {
            updatedUser.setFirst_name(user.getFirst_name());
        }
        if(user.getLast_name() != null) {
            updatedUser.setLast_name(user.getLast_name());
        }
        if(user.getArea_id() != 0) {
            updatedUser.setArea_id(user.getArea_id());
        }

        // Save the updated user to the userRepository.
        userRepository.save(updatedUser);

        // Convert the updated user to a UserDTO using the userMP utility class and return it.
        return UserMP.toUserDTO(updatedUser);
    }

}
