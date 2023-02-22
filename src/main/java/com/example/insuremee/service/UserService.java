package com.example.insuremee.service;

import com.example.insuremee.domains.Role;
import com.example.insuremee.domains.User;
import com.example.insuremee.dto.UserInfoDTO;
import com.example.insuremee.enums.UserRole;
import com.example.insuremee.exception.DuplicateEntityException;
import com.example.insuremee.exception.NoAccessException;
import com.example.insuremee.exception.NotFoundException;
import com.example.insuremee.repository.UserRepository;
import com.example.insuremee.security.SecurityService;
import com.example.insuremee.util.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final Mapper mapper;

    private final SecurityService securityService;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public List<UserInfoDTO> renderAllUsers() {
        List<User> users = getAll();
        return renderUsers(users);
    }

    public List<UserInfoDTO> renderUsers(List<User> users) {
        return users.stream().map(mapper::userToDTO).toList();
    }

    public User getUser(Long id) throws NotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()) {
            throw new NotFoundException("No user found");
        }
        return userOptional.get();
    }

    public void addNewUser(User user) throws DuplicateEntityException {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if(userOptional.isPresent()) {
            throw new DuplicateEntityException("email taken");
        }
        userRepository.save(user);
    }

    public UserInfoDTO renderUser(Long id) throws NotFoundException, NoAccessException {
        checkAccessToUser(id);
        User user = getUser(id);
        return mapper.userToDTO(user);
    }

    private void checkAccessToUser(Long userId) throws NoAccessException {
        User currentUser = securityService.getCurrentUser();
        boolean isAdmin = currentUser.getUserRoles().stream().map(Role::getAuthority).toList().contains(UserRole.ROLE_ADMIN.toString());
        boolean isSameUser = Objects.equals(currentUser.getId(), userId);
        if(!isSameUser && !isAdmin) {
            throw new NoAccessException("User don't have access to resource");
        }
    }

    public void updateUser(Long id, UserInfoDTO userInfo) throws NotFoundException, NoAccessException {
        checkAccessToUser(id);
        User user = getUser(id);

        String updatedLastName = userInfo.getLastName();
        String updatedFirstName = userInfo.getFirstName();
        String updatedEmail = userInfo.getEmail();

        if(updatedLastName != null && updatedLastName.length() > 0 && !Objects.equals(user.getLastName(),updatedLastName)) {
            user.setLastName(updatedLastName);
        }

        if(updatedFirstName != null && updatedFirstName.length() > 0 && !Objects.equals(user.getFirstName(),updatedFirstName)) {
            user.setLastName(updatedFirstName);
        }

        if(updatedEmail != null && updatedEmail.length() > 0 && !Objects.equals(user.getEmail(),updatedEmail)) {
            user.setLastName(updatedEmail);
        }

        userRepository.save(user);
    }

}
