package service;

import dao.UserDAO;
import entity.UserEntity;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import utils.PasswordHasher;

import java.util.Optional;

@Stateless
public class UserService {

    @Inject
    private UserDAO userDAO;

    public UserEntity register(String username, String password) {
        if (userDAO.findByUsername(username).isPresent()) {
            return null;
        }

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(PasswordHasher.hashPassword(password));
        userDAO.save(user);
        return user;
    }

    public UserEntity authenticate(String username, String password) {
        return userDAO.findByUsername(username)
                .filter(user -> PasswordHasher.verifyPassword(password, user.getPassword()))
                .orElse(null);
    }

    public UserEntity findUserById(Long userId) {
        return userDAO.findById(userId).orElse(null);
    }
}
