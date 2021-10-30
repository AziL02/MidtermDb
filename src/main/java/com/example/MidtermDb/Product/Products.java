package com.example.MidtermDb.Product;

import com.example.MidtermDb.Customers.Customers;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String products_name;
    private int quantity;
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customersId", nullable = false)
    private Customers customers;

    public Products() {
    }

    public Products(String products_name, int quantity, double price) {
        this.products_name = products_name;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProducts_name() {
        return products_name;
    }

    public void setProducts_name(String products_name) {
        this.products_name = products_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Products)) return false;
        Products products = (Products) o;
        return
                Objects.equals(products_name, products.products_name) &&
                        Objects.equals(quantity, products.quantity) &&
                        Objects.equals(price, products.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, products_name, quantity,price);
    }

    @Override
    public String toString() {
        return "Customers{" +
                "id=" + id +
                ", products_name='" + products_name + '\'' +
                ", quantity=" + quantity + '\'' +
                ", price=" + price + '\'' +
                '}';
    }
}
