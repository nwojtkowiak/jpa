package com.capgemini.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Year;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "CAR")
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@EntityListeners({UpdateListener.class, InsertListener.class})
public class CarEntity /*extends  AbstractEntity */ implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 45)
    private String mark;

    @Column(nullable = false, length = 30)
    private String model;

    @Column(nullable = false)
    private Year prodYear;

    @Column(nullable = false)
    private double capacity;

    @Column(nullable = false)
    private int power;

    @Column(nullable = false)
    private Long course;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private ColorEntity color;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private TypeEntity type;

    @OneToMany(targetEntity = LoanEntity.class, mappedBy = "car", cascade = CascadeType.REMOVE)
    private List<LoanEntity> loans = new LinkedList<>();

    // for hibernate
    public CarEntity() {
    }

    public CarEntity(Long id, String mark, String model, Year prodYear,
                     double capacity, int power, long course,
                     ColorEntity color, TypeEntity type, List<LoanEntity> loans) {
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.prodYear = prodYear;
        this.capacity = capacity;
        this.power = power;
        this.course = course;
        this.color = color;
        this.type = type;
        this.loans = loans;
    }

    public CarEntity(Long id, String mark, String model, Year prodYear,
                     double capacity, int power, long course){
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.prodYear = prodYear;
        this.capacity = capacity;
        this.power = power;
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public String getMark() {
        return mark;
    }

    public String getModel() {
        return model;
    }

    public Year getProdYear() {
        return prodYear;
    }

    public double getCapacity() {
        return capacity;
    }

    public int getPower() {
        return power;
    }

    public long getCourse() {
        return course;
    }

    public ColorEntity getColor() {
        return color;
    }

    public TypeEntity getType() {
        return type;
    }


    public void setColor(ColorEntity color) {
        this.color = color;
    }

    public void setType(TypeEntity type) {
        this.type = type;
    }

    public List<LoanEntity> getLoans() {
        return loans;
    }

    public void setLoans(List<LoanEntity> loans) {
        this.loans = loans;
    }
}