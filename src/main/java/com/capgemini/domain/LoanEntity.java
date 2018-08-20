package com.capgemini.domain;

import com.capgemini.listeners.InsertListener;
import com.capgemini.listeners.UpdateListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
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
    private Date dateFrom;

    @Column(nullable = true)
    private Date dateTo;

    @ManyToOne
    private CarEntity car;

    @ManyToOne
    private CustomerEntity customer;

    @Column(nullable = false)
    private double amount;


    // for hibernate
    public LoanEntity() {
    }

    public LoanEntity(Long id, Date dateFrom, Date dateTo, double amount) {
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

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
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

    public void setOfficeFrom(OfficeEntity officeFrom) {
        this.officeFrom = officeFrom;
    }

    public void setOfficeTo(OfficeEntity officeTo) {
        this.officeTo = officeTo;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
}