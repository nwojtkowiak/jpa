package com.capgemini.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "LOAN")
public class LoanEntity implements Serializable {

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

    public LoanEntity(Long id, String dateFrom, String dateTo, double amount) {
        this.id = id;
        this.dateFrom = Date.valueOf(dateFrom);
        this.dateTo = Date.valueOf(dateTo);
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

    public String getDateFrom() {
        return dateFrom.toString();
    }

    public String getDateTo() {
        return dateTo.toString();
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