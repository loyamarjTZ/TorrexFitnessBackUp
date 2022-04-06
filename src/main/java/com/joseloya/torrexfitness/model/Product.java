package com.joseloya.torrexfitness.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull
    @Size(min=1, max=30)
    private String name;

    @Column
    @NotNull
    @Size(min=1, max=500)
    private String description;

    @Column
    @NotNull
    @Size(min=1, max=10)
    private double price;

    @Column
    @NotNull
    @Size(min=1, max=500)
    private String filename;

    public Product(){
    }

    public Product(long id, String name, String description, double price, String filename) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.filename = filename;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Double.compare(product.price, price) == 0 && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(filename, product.filename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, filename);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", filename='" + filename + '\'' +
                '}';
    }
}
