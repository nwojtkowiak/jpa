package com.capgemini.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ADDRESS")
public class AddressEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    private String street;

    @Column(nullable = false)
    private int building;

    @Column(nullable = true)
    private int flat;

    @Column(nullable = true, length = 10)
    private String post_code;

    @Column(nullable = false, length = 50)
    private String city;

    public AddressEntity() {

    }

    public AddressEntity(Long id, String street, int building, int flat, String post_code, String city) {
        this.street = street;
        this.building = building;
        this.flat = flat;
        this.post_code = post_code;
        this.city = city;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public int getBuilding() {
        return building;
    }

    public int getFlat() {
        return flat;
    }

    public String getPost_code() {
        return post_code;
    }

    public String getCity() {
        return city;
    }
}
