package kata.pp.PP_312.service;

import kata.pp.PP_312.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    List<User> getAllUsers();
    void deleteUser(Long id);
    User findUserById(Long id);
}
