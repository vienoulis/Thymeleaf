package ru.vienoulis.crud_thymeleaf.dao;

import ru.vienoulis.crud_thymeleaf.dao.interfaces.RoleDao;
import ru.vienoulis.crud_thymeleaf.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImp implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getRoleByName(String role) {
        return (Role) entityManager.createQuery("from Role where role= :rl")
                .setParameter("rl", role).getSingleResult();
    }

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select role from Role ").getResultList();
    }
}
