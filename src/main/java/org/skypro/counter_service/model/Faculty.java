package org.skypro.counter_service.model;

import jakarta.validation.constraints.NotNull;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "faculty")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Color is mandatory")
    private String color;

    @OneToMany(mappedBy = "faculty")
    private List<Student> students;

    public Faculty() {}

    public Faculty(String name, String color) {
        this.name = name;
        this.color = color;
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
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}