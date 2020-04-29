package ru.vienoulis.crud_thymeleaf.dao;


import org.springframework.stereotype.Repository;
import ru.vienoulis.crud_thymeleaf.dao.interfaces.UserDao;
import ru.vienoulis.crud_thymeleaf.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User ").getResultList();
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void addUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(long userId) {
        entityManager.createQuery("delete from User where id = :id")
                .setParameter("id", userId).executeUpdate();
    }

    @Override
    public User getUserById(Long id) {

        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByName(String name) {
//        Set<Role> roleSet = new HashSet<>();
//        roleSet.add(new Role(1L, "ROLE_ADMIN" ));
//        roleSet.add(new Role(2L, "ROLE_USER"));
//
//        User user = new User();
//        user.setName(name);
//        user.setId(33);
//        user.setRoleSet(roleSet);
//        user.setAge(1);
//        user.setPassword("1");
//        User user = entityManager.find(User.class, 33L);
        User user = (User) entityManager.createQuery("from User user where name = :nm")
                .setParameter("nm", name)
                .getSingleResult();

//        return (User) entityManager.createQuery("select User from User where name = :nm")
//                .setParameter("nm", name).getSingleResult();
        return user;
    }

}
