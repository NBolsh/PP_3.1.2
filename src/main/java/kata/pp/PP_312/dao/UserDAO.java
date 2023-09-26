package kata.pp.PP_312.dao;

import kata.pp.PP_312.model.User;
import java.util.List;
import java.util.Optional;

public interface UserDAO {
    void addUser(User user);
    List<User> getAllUsers();
    void deleteUser(Long id);
    User findUserById(Long id);

    Optional<User> findUserByEmail(String email);
}

