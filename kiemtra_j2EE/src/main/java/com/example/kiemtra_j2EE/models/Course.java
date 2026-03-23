package com.example.kiemtra_j2EE.models;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "course")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String urlImage;

    private Double credits;

    private String lecture;

    @jakarta.persistence.ManyToOne
    @jakarta.persistence.JoinColumn(name = "category_id")
    private Category category;

    // public Course (){

    // }
    // public Course(Long id, String name, String urlImage, Double credits, String lecture, Category category) {
    //     this.id = id;
    //     this.name = name;
    //     this.credits = credits;
    //     this.lecture = lecture;
    //     this.category = category;
    // }
    public Long getId() {
        return id;
    }
}
