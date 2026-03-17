package com.ecommerce.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Category") // Marks this class as a JPA entity and maps it to a table named "Category"
@Data // Lombok annotation to automatically generate getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor // Lombok annotation to generate a no-args constructor
@AllArgsConstructor // Lombok annotation to generate an all-args constructor
public class Category {
    @Id // Marks categoryId as the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies that the primary key is auto-incremented by the database
    private Long categoryId;// Field for storing the unique ID of each category
    @NotBlank
    @Size(min=5, message = "Category name must contains atleast 5 characters")
    //@Size(min = 5)
    private String categoryName; // Field for storing the name of the category
}