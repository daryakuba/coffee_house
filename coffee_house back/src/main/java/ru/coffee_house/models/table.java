package ru.coffee_house.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Tables", schema = "public")
public class table {
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "number")
    private int number;
    @Column(name = "status")
    private String status;

    @Id
    @SequenceGenerator(name = "tables_seq", sequenceName = "tables_sequence", allocationSize = 1)
    @GeneratedValue(generator = "tables_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    public table(){}

    public table(int number, String status, User user){
        this.number = number;
        this.status = status;
        this.user = user;
    }

    public table(int number, Long id, String status, User user){
        this.number = number;
        this.id = id;
        this.status = status;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}