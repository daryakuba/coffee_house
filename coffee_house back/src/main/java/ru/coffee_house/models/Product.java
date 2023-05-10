package ru.coffee_house.models;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "Products", schema = "public")
public class Product {
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private int price;
    @Column(name = "bonuses")
    private int bonuses;
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name="category_id", nullable = false)
    private Category category;

    @Id
    @SequenceGenerator(name = "products_seq", sequenceName = "products_sequence", allocationSize = 1)
    @GeneratedValue(generator = "products_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    public Product() {
    }

    public Product(String name, int price, String description, Category category, long id, int bonuses) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.bonuses = bonuses;
        this.id = id;
    }

    public Product(String name, String description, int price, Category category, int bonuses) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.bonuses = bonuses;
    }

    public String getName() {return name;}

    public Category getCategory(){
        return this.category;
    }

    public void setCategory(Category category){
        this.category = category;
    }

    public int getPrice() {return price;}

    public String getDescription() {
        return description;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBonuses(int bonuses) {
        this.bonuses = bonuses;
    }

    public int getBonuses() {
        return bonuses;
    }
}
