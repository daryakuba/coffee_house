package ru.coffee_house.models;

import java.util.List;
import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "Orders", schema = "public")
public class Order {
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @Column(name = "bonuses")
    private int bonuses;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "product_in_order",
            joinColumns = { @JoinColumn(name = "order_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") }
    )
    List<Product> products;

    @Id
    @SequenceGenerator(name = "order_seq", sequenceName = "order_sequence", allocationSize = 1)
    @GeneratedValue(generator = "order_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    public Order(){}

    public Order(Long id, User user, List<Product> products) {
        this.id = id;
        this.products = products;
        this.user = user;
        this.bonuses = 0;
    }

    public Order(User user, List<Product> products) {
        this.user = user;
        this.products = products;
        this.bonuses = 0;
    }

    public Long getId() {
        return id;
    }


    public User getUser(){
        return  this.user;
    }

    public void setUser(User user){
        this.user = user;;
    }

    public List<Product> getProducts(){return this.products;}

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product){
        this.products.add(product);
    }

    public int getBonuses() {
        return bonuses;
    }

    public void setBonuses(int bonuses) {
        this.bonuses = bonuses;
    }
}