package com.inn.cafe.pojo;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "\"category\"")
public class Category implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    public Category() {

    }

    public Category(String name) {
        this.name = name;
    }

    @Column(name = "name")
    private String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

}
