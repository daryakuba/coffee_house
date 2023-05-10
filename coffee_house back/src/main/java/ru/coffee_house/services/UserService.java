package ru.coffee_house.services;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.coffee_house.models.User;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.transaction.Transactional;

@Component
@Service
public class UserService {
    private final SessionFactory sessionFactory;
    private Session session;

    public UserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @PostConstruct
    public void init() {
        session = sessionFactory.openSession();
    }

    @PreDestroy
    public void unSession() {
        session.close();
    }

    @Transactional
    public void addUser(User user) {
        session.beginTransaction();
        session.saveOrUpdate(user);
        session.getTransaction().commit();
    }

    public List<User> getUsers() {
        return session.createQuery("select u from User u", User.class).list();
    }

    public User getUser(long id) {
        return session.createQuery("select u from User u where u.id ='" + id + "'", User.class).getSingleResult();
    }
    public User getUser(String password, String login) {
        return (session.createQuery("select u from User u where u.login ='" + login + "' u.password ='" + password + "'", User.class).getSingleResult());
    }

    public Long getIdByLogin(String login) {
        return (session.createQuery("select u from User u where u.login ='" + login + "'", User.class).getSingleResult()).getId();
    }

    public User getUser(String login) {
        return (session.createQuery("select u from User u where u.login ='" + login + "'", User.class).getSingleResult());
    }

    public int getBonusesPerUser(long user_id){

        return session.get(User.class, user_id).getBonuses();
    }

    @Transactional
    public void deleteUser(long id) {
        session.beginTransaction();

        User temp = session.load(User.class, id);
        session.delete(temp);

        session.getTransaction().commit();
    }
}