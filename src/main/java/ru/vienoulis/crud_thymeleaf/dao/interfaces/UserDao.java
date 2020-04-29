package ru.vienoulis.crud_thymeleaf.dao.interfaces;
import ru.vienoulis.crud_thymeleaf.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void addUser(User user);

    void delete(long userId);

    User getUserById(Long id);

    void update(User user);

    User getUserByName(String s);

}
