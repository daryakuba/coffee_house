package ru.coffee_house.services;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.coffee_house.models.User;
import ru.coffee_house.models.table;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Component
@Service
public class TableService {
    private final SessionFactory sessionFactory;
    private Session session;

    public TableService(SessionFactory sessionFactory) {
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
    public void addTable(table table) {
        session.beginTransaction();
        session.saveOrUpdate(table);
        session.getTransaction().commit();
    }

    public List<table> getTables() {
        return session.createQuery("select t from table t", table.class).list();
    }

    public table getTable(long id) {
        return session.createQuery("select t from table t where t.id ='" + id + "'", table.class).getSingleResult();
    }

    @Transactional
    public void deleteTable(long id) {
        session.beginTransaction();
        table temp = session.load(table.class, id);
        session.delete(temp);
        session.getTransaction().commit();
    }

    @Transactional
    public void freeStatus(Long table_id){
        session.beginTransaction();
        table table = session.get(table.class, table_id);
        table.setStatus("Свободен");
        session.update(table);
        session.getTransaction().commit();
    }

    public List<table> getRentedTables() {
        List<table> temp = session.createQuery("select t from table t where t.status = 'Забронирован'", table.class).list();
        return temp;
    }

    public String isFreeTableExist() {
        List<table> freeTables = session.createQuery("select t from table t where t.status = 'Свободен'", table.class).list();
        if (freeTables.size() == 0){
            return "ERROR";
        }
        else return "OK";
    }
    public table getFreeTable() {
        List<table> freeTables = session.createQuery("select t from table t where t.status = 'Свободен'", table.class).list();
        return freeTables.get(0);
    }

    public Long getUsersTable(Long user_id) {
        Query query = session.createQuery("select count(*) from table t where t.user.id = '" + user_id + "'");
        return (Long)query.getSingleResult();
    }

    public int getNumOfTable(Long user_id) {
        table table = session.createQuery("select t from table t where t.user.id = '" + user_id + "'", table.class).getSingleResult();
        return table.getNumber();
    }

    @Transactional
    public void addUser(Long table_id, Long user_id){
        session.beginTransaction();
        table temp = session.get(table.class, table_id);
        temp.setUser(session.get(User.class, user_id));
        session.update(temp);
        session.getTransaction().commit();
    }

    @Transactional
    public void rentStatus(Long table_id) {
        session.beginTransaction();
        table table = session.get(table.class, table_id);
        table.setStatus("Забронирован");
        session.update(table);
        session.getTransaction().commit();
    }

    @Transactional
    public void updateUser(Long table_id){
        session.beginTransaction();
        table table = session.get(table.class, table_id);
        User user = table.getUser();
        user = null;
        table.setUser(user);
        session.update(table);
        session.getTransaction().commit();
    }
}
