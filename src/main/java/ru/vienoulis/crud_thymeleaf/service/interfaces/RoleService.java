package ru.vienoulis.crud_thymeleaf.service.interfaces;

import ru.vienoulis.crud_thymeleaf.model.Role;

import java.util.List;

public interface RoleService {

    Role getRoleByName(String role_admin);

    List<Role> getAllRoles();
}
