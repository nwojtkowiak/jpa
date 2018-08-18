package com.capgemini.types;

import java.util.Date;
import java.util.List;

public class EmployeeTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDay;
    private Long address;
    private Long office;
    private Long position;
    private List<Long> cars;

    public EmployeeTO(String firstName, String lastName, Date birthDay, Long address, Long office,
                      Long position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.address = address;
        this.office = office;
        this.position = position;
    }

    public EmployeeTO(Long id, String firstName, String lastName,
                      Date birthDay, Long address,
                      Long office, Long position,
                      List<Long> cars) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.address = address;
        this.office = office;
        this.position = position;
        this.cars = cars;
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

    public Date getBirthDay() {
        return birthDay;
    }

    public Long getAddress() {
        return address;
    }

    public Long getOffice() {
        return office;
    }

    public Long getPosition() {
        return position;
    }

    public static class EmployeeTOBuilder {

        private Long id;
        private String firstName;
        private String lastName;
        private Date birthDay;
        private Long address;
        private Long office;
        private Long position;
        private List<Long> cars;

        public EmployeeTOBuilder() {
            super();
        }

        public EmployeeTOBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public EmployeeTOBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public EmployeeTOBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public EmployeeTOBuilder withBirthDay(Date birthDay) {
            this.birthDay = birthDay;
            return this;
        }

        public EmployeeTOBuilder withAddress(Long address) {
            this.address = address;
            return this;
        }

        public EmployeeTOBuilder withOffice(Long office) {
            this.office = office;
            return this;
        }

        public EmployeeTOBuilder withPosition(Long position) {
            this.position = position;
            return this;
        }

        public EmployeeTOBuilder withCars(List<Long> cars) {
            this.cars = cars;
            return this;
        }


        public EmployeeTO build() {
            checkBeforeBuild(firstName, lastName, birthDay, address, position);
            return new EmployeeTO(id, firstName, lastName, birthDay, address, office, position, cars);
        }

        private void checkBeforeBuild(String firstName, String lastName, Date birthDay, Long address, Long position) {
            if (firstName == null || lastName == null || birthDay == null || address == null ||  position == null) {
                throw new RuntimeException("Incorrect employee to be created");
            }

        }

    }
}
