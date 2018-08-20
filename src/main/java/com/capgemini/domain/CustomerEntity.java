package com.capgemini.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "CUSTOMER")
public class CustomerEntity  implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 45)
    private String firstName;

    @Column(nullable = false, length = 45)
    private String lastName;

    @Column(nullable = false, length = 45)
    private String email;

    @Column(nullable = false, length = 20)
    private String phoneNumber;

    @Column(nullable = false)
    private Date birthDay;

    @Column(nullable = false, length = 20)
    private String creditCard;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private AddressEntity address;

    @OneToMany(targetEntity = LoanEntity.class, mappedBy = "customer", cascade = CascadeType.REMOVE)
    private List<LoanEntity> loans = new LinkedList<>();

    // for hibernate
    public CustomerEntity() {
    }

    public CustomerEntity(Long id, String firstName, String lastName, String email,
                          String phoneNumber, String birthDay, String creditCard) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDay = Date.valueOf(birthDay);
        this.creditCard = creditCard;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBirthDay() {
        return birthDay.toString();
    }

    public String getCreditCard() {
        return creditCard;
    }

    public String getEmail() {
        return email;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }
}
