package com.capgemini.domain;

import com.capgemini.listeners.InsertListener;
import com.capgemini.listeners.UpdateListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "EMPLOYEE")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({UpdateListener.class, InsertListener.class})
public class EmployeeEntity extends AbstractEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 45)
    private String firstName;
    @Column(nullable = false, length = 45)
    private String lastName;
    @Column(nullable = false)
    //@Temporal(TemporalType.TIMESTAMP)
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


    @ManyToMany //co ustawić w usuwaniu?
    @JoinTable(name = "KEEPER", joinColumns = {@JoinColumn(name = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "car_id")})
    private List<CarEntity> cars;


    // for hibernate
    public EmployeeEntity() {
        this.cars = new ArrayList<>();
    }

    public EmployeeEntity(Long id, String firstName, String lastName, Date birthDay, AddressEntity address, OfficeEntity office,
                          PositionEntity position) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.address = address;
        this.office = office;
        this.position = position;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public Date getBirthDay() {
        return birthDay;
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

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
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

    public void setCars(List<CarEntity> cars) {
        this.cars = cars;
    }
}
