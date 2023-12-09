package com.strangely.backend.DataAccess.User.Implementation;


import com.strangely.backend.DataAccess.User.IUserDAO;
import com.strangely.backend.Model.Auth.Entities.ID;
import com.strangely.backend.Model.User.Entities.User;
import com.strangely.backend.Repository.User.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDAO implements IUserDAO {

    private EntityManager entityManager;
    private UserRepository userRepository;

    @Autowired
    public UserDAO(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> q = entityManager.createQuery("FROM User", User.class);
        List<User> u = q.getResultList();
        return u;
    }

    @Transactional
    @Override
    public User Insert(User u) {
        entityManager.merge(u);
        return u;
    }

    @Override
    public void Delete(int user_id) {
        User user = entityManager.find(User.class, user_id);
        entityManager.remove(user);
    }

    @Override
    public User find(int id) {
        User u =entityManager.find(User.class, id);
        return u;
    }
@Override
public Optional<User> findBy(String value, ID id) {
    List<User> users = findAll();
    Optional<User> result = switch (id) {
        case Email -> users.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(value))
                .findFirst();
        case Username -> users.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(value))
                .findFirst();
        case ResetToken -> users.stream()
                .filter(user -> user.getResetToken().equalsIgnoreCase(value))
                .findFirst();
    };
    return Optional.ofNullable(result.get());
}
}
