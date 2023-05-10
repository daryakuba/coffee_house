package ru.coffee_house.services;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.coffee_house.models.Order;
import ru.coffee_house.models.Product;
import ru.coffee_house.models.User;

import javax.transaction.Transactional;

@Component
@Service
public class OrderService {
    private final SessionFactory sessionFactory;
    private Session session;

    public OrderService(SessionFactory sessionFactory) {
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
    public void addOrder(Order order) {
        session.beginTransaction();
        session.save(order);
        session.getTransaction().commit();

    }

    @Transactional
    public void addProductToOrder(Long user_id, Long product_id) {
        session.beginTransaction();
        Order order = session.createQuery("select o from Order o where o.user.id ='" + user_id + "' and o.bonuses = 0", Order.class).getSingleResult();
        order.addProduct(session.get(Product.class, product_id));
        session.update(order);
        session.saveOrUpdate(order);
        session.getTransaction().commit();

    }

    public List<Order> getUsersOrders(Long user_id) {
        return session.createQuery("select o from Order o where o.user ='" + user_id + "'", Order.class).list();
    }

    public List<Order> getOrders() {
        return session.createQuery("select o from Order o", Order.class).list();
    }

    public Order getOrder(long id) {
        return session.createQuery("select o from Order o where o.id ='" + id + "'", Order.class).getSingleResult();
    }

    @Transactional
    public void setAllUsersBonuses(long user_id){
        session.beginTransaction();
        List<Order> orders = session.createQuery("select o from Order o where o.user ='" + user_id + "'", Order.class).list();
        int bonuses = 0;
        for (Order order : orders) {
            bonuses += order.getBonuses();
        }
        User user = session.get(User.class, user_id);
        user.setBonuses(bonuses);
        session.update(user);
        session.saveOrUpdate(user);
        session.getTransaction().commit();
    }

    @Transactional
    public void setBonuses(long user_id){
        session.beginTransaction();
        Order order = session.createQuery("select o from Order o where o.user.id ='" + user_id + "' and o.bonuses = 0", Order.class).getSingleResult();
        List<Product> products = order.getProducts();
        int bonuses = 0;
        for (Product product : products) {
            bonuses += product.getBonuses();
        }
        order.setBonuses(bonuses);
        session.update(order);
        session.saveOrUpdate(order);
        session.getTransaction().commit();
    }

    @Transactional
    public void deleteOrder(long id) {
        session.beginTransaction();
        session.createQuery("delete from Order where id = :id").setParameter("id", id).executeUpdate();
        session.getTransaction().commit();
    }
}
