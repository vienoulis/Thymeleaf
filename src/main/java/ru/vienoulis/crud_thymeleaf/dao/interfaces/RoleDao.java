package ru.vienoulis.crud_thymeleaf.dao.interfaces;
import ru.vienoulis.crud_thymeleaf.model.Role;

import java.util.List;

public interface RoleDao {

    Role getRoleByName(String s);

    List<Role> getAllRoles();

}
