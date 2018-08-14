package com.capgemini.domain;

import com.capgemini.listeners.InsertListener;
import com.capgemini.listeners.UpdateListener;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.Year;
import java.util.Collection;

@Entity
@Table(name = "CAR")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({UpdateListener.class, InsertListener.class})
public class CarEntity extends  AbstractEntity  implements Serializable {


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
    private BigInteger course;

    public CarEntity(String mark, String model, Year prodYear, double capacity, int power, BigInteger course, ColorEntity color, TypeEntity type) {
        this.mark = mark;
        this.model = model;
        this.prodYear = prodYear;
        this.capacity = capacity;
        this.power = power;
        this.course = course;
        this.color = color;
        this.type = type;
    }

    @OneToOne
    @JoinColumn(name = "color_id", nullable = false)
    private ColorEntity color;

    @OneToOne
    @JoinColumn(name = "type_id", nullable = false)
    private TypeEntity type;

    @ManyToMany(mappedBy = "cars")
    private Collection<EmployeeEntity> keepers;

    //czy trzeba robic bidirectional, czy moze byc bez tego i unidirectional
    /*@OneToMany(targetEntity = LoanEntity.class, mappedBy = "car", cascade = CascadeType.ALL)
    private Set<LoanEntity> loans = new HashSet<>();*/

    // for hibernate
    public CarEntity() {
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

    public BigInteger getCourse() {
        return course;
    }

    public ColorEntity getColor() {
        return color;
    }

    public TypeEntity getType() {
        return type;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setProdYear(Year prodYear) {
        this.prodYear = prodYear;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setCourse(BigInteger course) {
        this.course = course;
    }

    public void setColor(ColorEntity color) {
        this.color = color;
    }

    public void setType(TypeEntity type) {
        this.type = type;
    }

    public void setKeepers(Collection<EmployeeEntity> keepers) {
        this.keepers = keepers;
    }
}