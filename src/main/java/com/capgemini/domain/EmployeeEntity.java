package com.capgemini.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EMPLOYEE")
public class EmployeeEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 45)
    private String firstName;

    @Column(nullable = false, length = 45)
    private String lastName;

    @Column(nullable = false)
    private Date birthDay;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @ManyToOne
    @JoinColumn(name = "office_id")
    private OfficeEntity office;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private PositionEntity position;


    @ManyToMany
    @JoinTable(name = "KEEPER", joinColumns = {@JoinColumn(name = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "car_id")})
    private List<CarEntity> cars;


    // for hibernate
    public EmployeeEntity() {
        this.cars = new ArrayList<>();
    }

    public EmployeeEntity(Long id, String firstName, String lastName, String birthDay) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = Date.valueOf(birthDay);
        this.cars = new ArrayList<>();
    }


    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public String getBirthDay() {
        return birthDay.toString();
    }

    public OfficeEntity getOffice() {
        return office;
    }

    public PositionEntity getPosition() {
        return position;
    }

    public List<CarEntity> getCars() {
        return cars;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public void setOffice(OfficeEntity office) {
        this.office = office;
    }

    public void setPosition(PositionEntity position) {
        this.position = position;
    }

}
