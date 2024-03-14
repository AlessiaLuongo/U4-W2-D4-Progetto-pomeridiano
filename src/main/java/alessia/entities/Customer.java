package alessia.entities;

import com.github.javafaker.Name;

import java.util.Random;
import java.util.function.Supplier;

public class Customer {
    private Long id;
    private String name;
    private Integer tier;

// CONSTRUCTORS

    public Customer(String name, Name named) {
        Random idRandomNumber = new Random();
        this.id = idRandomNumber.nextLong();
        this.name = name;
        this.tier = setTier();
    }


// GETTERS & SETTERS


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

    public Integer getTier() {
        return tier;
    }

    public int setTier() {
       Random randomTinyNumber = new Random();
       return randomTinyNumber.nextInt(1,4);
    }

// TO STRING


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tier=" + tier +
                '}';
    }
}
