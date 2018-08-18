package com.capgemini.domain;

import com.capgemini.listeners.InsertListener;
import com.capgemini.listeners.UpdateListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "LOAN")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({UpdateListener.class, InsertListener.class})
public class LoanEntity extends AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private OfficeEntity officeFrom;

    @ManyToOne
    private OfficeEntity officeTo;

    @Column(nullable = false)
    private Timestamp dateFrom;

    @Column(nullable = true)
    private Timestamp dateTo;

    @ManyToOne
    private CarEntity car;

    @ManyToOne
    private CustomerEntity customer;

    @Column(nullable = false)
    private double amount;


    // for hibernate
    public LoanEntity() {
    }

    public LoanEntity(Long id, Timestamp dateFrom, Timestamp dateTo, double amount) {
        this.id = id;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public OfficeEntity getOfficeFrom() {
        return officeFrom;
    }

    public OfficeEntity getOfficeTo() {
        return officeTo;
    }

    public Timestamp getDateFrom() {
        return dateFrom;
    }

    public Timestamp getDateTo() {
        return dateTo;
    }

    public CarEntity getCar() {
        return car;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public double getAmount() {
        return amount;
    }
}