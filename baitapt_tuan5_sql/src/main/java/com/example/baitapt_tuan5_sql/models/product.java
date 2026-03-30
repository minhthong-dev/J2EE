package com.example.baitapt_tuan5_sql.models;

import  com.example.baitapt_tuan5_sql.models.category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "`san pham`")
public class product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`ten san pham`", nullable = false, length = 100)
    private String name;

    @Column(name = "`gia san pham`", nullable = false)
    private Double price;

    @Column(name = "`hinh anh san pham`")
    private String path;

    @jakarta.persistence.ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
    @jakarta.persistence.JoinColumn(name = "`category_id`", nullable = false)
    private category category;

    @Transient
    private Double discountedPrice;

    public product() {
    }

    public product(String name, Double price, String path, category category) {
        this.name = name;
        this.price = price;
        this.path = path;
        this.category = category;
    }

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(Double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }
    public category getCategory(){
        return category;
    }
    public void setCategory(category category){
        this.category = category;
    }
}
