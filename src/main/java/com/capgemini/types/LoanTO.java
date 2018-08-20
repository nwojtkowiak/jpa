package com.capgemini.types;

import java.sql.Date;
import java.sql.Timestamp;

public class LoanTO {

    private Long id;

    private long officeFrom;

    private long officeTo;

    private Date dateFrom;

    private Date dateTo;

    private long car;

    private long customer;

    private double amount;

    public LoanTO(){

    }

    public LoanTO(Long id, long officeFrom, long officeTo, Date dateFrom, Date dateTo, long car, long customer, double amount) {
        this.id = id;
        this.officeFrom = officeFrom;
        this.officeTo = officeTo;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.car = car;
        this.customer = customer;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getOfficeFrom() {
        return officeFrom;
    }

    public void setOfficeFrom(long officeFrom) {
        this.officeFrom = officeFrom;
    }

    public long getOfficeTo() {
        return officeTo;
    }

    public void setOfficeTo(long officeTo) {
        this.officeTo = officeTo;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public long getCar() {
        return car;
    }

    public void setCar(long car) {
        this.car = car;
    }

    public long getCustomer() {
        return customer;
    }

    public void setCustomer(long customer) {
        this.customer = customer;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public static class LoanToBuilder {

        private Long id;

        private long officeFrom;

        private long officeTo;

        private Date dateFrom;

        private Date dateTo;

        private long car;

        private long customer;

        private double amount;


        public LoanToBuilder() {
            super();
        }

        public LoanToBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public LoanToBuilder withOfficeFrom(Long officeFrom) {
            this.officeFrom = officeFrom;
            return this;
        }

        public LoanToBuilder withOfficeTo(Long officeTo) {
            this.officeTo = officeTo;
            return this;
        }

        public LoanToBuilder withCustomer(Long customer) {
            this.customer = customer;
            return this;
        }

        public LoanToBuilder withCar(Long car) {
            this.car = car;
            return this;
        }

        public LoanToBuilder withAmount(Double amount) {
            this.amount = amount;
            return this;
        }

        public LoanToBuilder withDateFrom(Date dateFrom) {
            this.dateFrom = dateFrom;
            return this;
        }

        public LoanToBuilder withDateTo(Date dateTo) {
            this.dateTo = dateTo;
            return this;
        }


        public LoanTO build() {
            checkBeforeBuild(officeFrom, dateFrom, dateTo, car, customer, amount);
            return new LoanTO(id, officeFrom,officeTo, dateFrom, dateTo, car, customer, amount);
        }

        private void checkBeforeBuild(Long officeFrom, Date dateFrom, Date dateTo, Long car, Long customer, Double amount) {
            if (officeFrom == null || dateFrom == null || dateTo == null || car == null || customer == null || amount == null) {
                throw new RuntimeException("Incorrect loan be created");
            }
        }
    }
}
