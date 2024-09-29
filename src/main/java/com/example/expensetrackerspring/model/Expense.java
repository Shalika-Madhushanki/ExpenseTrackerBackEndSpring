package com.example.expensetrackerspring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.lang.NonNull;

@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment or sequence
    private Long id;

    @NotNull(message= "Description cannot be null")
    @Size(min=1, max=255, message = "Description must be between 1 and 255 characters")
    private String description;

    private String comments;
    @NotNull(message= "Category cannot be null")
    private String category;

    @NotNull(message= "Paidby cannot be null")
    private String paidBy;

    @Min(value = 0, message = "Value should not be greater than or equals to 0")
    private Double amount;

    public Expense(String description, String comments, String category, String paidBy, Double amount) {
        this.description = description;
        this.comments = comments;
        this.category = category;
        this.paidBy = paidBy;
        this.amount = amount;
    }

    public Expense() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
