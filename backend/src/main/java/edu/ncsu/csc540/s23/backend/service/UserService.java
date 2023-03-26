package edu.ncsu.csc540.s23.backend.service;

import edu.ncsu.csc540.s23.backend.dto.UserDTO;
import edu.ncsu.csc540.s23.backend.model.User;
import edu.ncsu.csc540.s23.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.userId = user.getUserId();
            userDTO.firstName = user.getFirstName();
            userDTO.lastName = user.getLastName();
            userDTO.emailId = user.getEmailId();
            userDTO.phoneNum = user.getPhoneNum();
            userDTO.regDate = user.getRegDate();

            return userDTO;
        }).toList();
    }

    public String createNewUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.firstName);
        user.setLastName(userDTO.lastName);
        user.setEmailId(userDTO.emailId);
        user.setPhoneNum(userDTO.phoneNum);
        user.setRegDate(userDTO.regDate);

        user = userRepository.save(user);
        return "User created successfully. User Id: " + user.getUserId().toString();
    }
}
