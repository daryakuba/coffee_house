package ru.coffee_house.models;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "Users", schema = "public")
public class User {
    @Column(name = "name")
    private String name;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "phone_number")
    private String phone_number;
    @Column(name = "status")
    private String status;
    @Column(name = "bonuses")
    private int bonuses;

    @Id
    @SequenceGenerator(name = "users_seq", sequenceName = "users_sequence", allocationSize = 1)
    @GeneratedValue(generator = "users_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    public User() {
    }

    public User(String name, String login, String password, String phone_number, String status, long id, int bonuses) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.phone_number = phone_number;
        this.status = status;
        this.bonuses = bonuses;
        this.id = id;
    }

    public User(String name, String login, String password, String phone_number, String status, int bonuses) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.phone_number = phone_number;
        this.status = status;
        this.bonuses = bonuses;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {return password;}

    public String getPhone_number() {return phone_number;}

    public String getStatus() {return status;}

    public Long getId(){
        return id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getBonuses() {
        return bonuses;
    }

    public void setBonuses(int bonuses) {
        this.bonuses = bonuses;
    }
}
