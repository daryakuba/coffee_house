package ru.coffee_house.models;

import javax.persistence.*;
import javax.persistence.Table;

import java.util.List;

@Entity
@Table(name = "Categories", schema = "public")
public class Category {
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    @Id
    @SequenceGenerator(name = "categories_seq", sequenceName = "categories_sequence", allocationSize = 1)
    @GeneratedValue(generator = "categories_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    public Category() {
    }

    public Category(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {return name;}

    public Long getId(){
        return id;
    }
}