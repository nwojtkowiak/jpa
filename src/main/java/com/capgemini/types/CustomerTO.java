package com.capgemini.types;

import java.sql.Date;
import java.util.List;

public class CustomerTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date birthDay;
    private String creditCard;
    private Long address;
    private List<Long> loans;

    public CustomerTO() {
    }

    public CustomerTO(Long id, String firstName, String lastName, String email,
                      String phoneNumber, String birthDay, String creditCard,
                      Long address, List<Long> loans) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDay = Date.valueOf(birthDay);
        this.creditCard = creditCard;
        this.address = address;
        this.loans = loans;
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

    public String getEmail() {
        return email;
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

    public Long getAddress() {
        return address;
    }

    public List<Long> getLoans() {
        return loans;
    }

    public static class CustomerTOBuilder {

        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private Date birthDay;
        private String creditCard;
        private Long address;
        private List<Long> loans;

        public CustomerTOBuilder() {
            super();
        }

        public CustomerTOBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public CustomerTOBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public CustomerTOBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public CustomerTOBuilder withBirthDay(String birthDay) {
            this.birthDay = Date.valueOf(birthDay);
            return this;
        }

        public CustomerTOBuilder withAddress(Long address) {
            this.address = address;
            return this;
        }

        public CustomerTOBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public CustomerTOBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public CustomerTOBuilder withCreditCard(String creditCard) {
            this.creditCard = creditCard;
            return this;
        }


        public CustomerTO build() {
            checkBeforeBuild(firstName, lastName, birthDay, address, email, phoneNumber, creditCard);
            return new CustomerTO(id, firstName,lastName, email, phoneNumber, birthDay.toString(), creditCard, address, loans);
        }

        private void checkBeforeBuild(String firstName, String lastName, Date birthDay, Long address,
                                      String email, String phoneNumber, String creditCard) {
            if (firstName == null || lastName == null || birthDay == null || address == null
                    ||  email == null || phoneNumber == null || creditCard == null) {
                throw new RuntimeException("Incorrect customer to be created");
            }

        }


    }
}
