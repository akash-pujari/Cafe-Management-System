package com.inn.cafe.wrapper;

public class ProductWrapper {

    private Integer id;
    private String name;
    private String status;
    private Integer category_fk;
    private String description;
    private double price;

    public ProductWrapper(Integer id, String name, String status, Integer category_fk, String description, double price) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.category_fk = category_fk;
        this.description = description;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCategory_fk() {
        return category_fk;
    }

    public void setCategory_fk(Integer category_fk) {
        this.category_fk = category_fk;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
