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

    public LoanTO(Long id, long officeFrom, long officeTo, String dateFrom, String dateTo, long car, long customer, double amount) {
        this.id = id;
        this.officeFrom = officeFrom;
        this.officeTo = officeTo;
        this.dateFrom = Date.valueOf(dateFrom);
        this.dateTo = Date.valueOf(dateTo);
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

    public long getOfficeTo() {
        return officeTo;
    }

    public String getDateFrom() {
        return dateFrom.toString();
    }

    public String getDateTo() {
        return dateTo.toString();
    }


    public long getCar() {
        return car;
    }

    public long getCustomer() {
        return customer;
    }

    public double getAmount() {
        return amount;
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

        public LoanToBuilder withDateFrom(String dateFrom) {
            this.dateFrom = Date.valueOf(dateFrom);
            return this;
        }

        public LoanToBuilder withDateTo(String dateTo) {
            this.dateTo = Date.valueOf(dateTo);
            return this;
        }


        public LoanTO build() {
            checkBeforeBuild(officeFrom, dateFrom, dateTo, car, customer, amount);
            return new LoanTO(id, officeFrom,officeTo, dateFrom.toString(), dateTo.toString(), car, customer, amount);
        }

        private void checkBeforeBuild(Long officeFrom, Date dateFrom, Date dateTo, Long car, Long customer, Double amount) {
            if (officeFrom == null || dateFrom == null || dateTo == null || car == null || customer == null || amount == null) {
                throw new RuntimeException("Incorrect loan be created");
            }
        }
    }
}
