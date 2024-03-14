package alessia.entities;

import java.util.Random;

public class Product {
    private Long id;
    private String name;
    private String category;
    private Double price;

// CONSTRUCTORS


    public Product(String name, String category, Double price) {
        Random idRandomNumber = new Random();
        this.id = idRandomNumber.nextLong();;
        this.name = name;
        this.category = category;
        this.price = price;
    }

//GETTERS & SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

// TO STRING


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}

