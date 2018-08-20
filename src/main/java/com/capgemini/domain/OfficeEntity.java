package com.capgemini.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "OFFICE")
public class OfficeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 20)
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @OneToMany(targetEntity = LoanEntity.class, mappedBy = "officeFrom", cascade = CascadeType.REMOVE)
    private List<LoanEntity> loansFrom = new LinkedList<>();

    @OneToMany(targetEntity = LoanEntity.class, mappedBy = "officeTo", cascade = CascadeType.REMOVE)
    private List<LoanEntity> loansTo = new LinkedList<>();

    @OneToMany(targetEntity = EmployeeEntity.class, mappedBy = "office", cascade = CascadeType.REMOVE)
    private List<EmployeeEntity> employees;


    public OfficeEntity() {
        this.employees = new LinkedList<>();
    }

    public OfficeEntity(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.employees = new LinkedList<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public List<EmployeeEntity> getEmployees() {
        return employees;
    }
}
