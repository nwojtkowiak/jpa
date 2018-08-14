package com.capgemini.types;

import com.sun.jndi.cosnaming.IiopUrl;

import java.util.Collection;
import java.util.Date;

public class EmployeeTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDay;
    private AddressTO address;
    private OfficeTO office;
    private PositionTO position;
    private Collection<CarTO> cars;

    public EmployeeTO(String firstName, String lastName, Date birthDay, AddressTO address, OfficeTO office,
                      PositionTO position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.address = address;
        this.office = office;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public AddressTO getAddress() {
        return address;
    }

    public void setAddress(AddressTO address) {
        this.address = address;
    }

    public OfficeTO getOffice() {
        return office;
    }

    public void setOffice(OfficeTO office) {
        this.office = office;
    }

    public PositionTO getPosition() {
        return position;
    }

    public void setPosition(PositionTO position) {
        this.position = position;
    }

    public Collection<CarTO> getCars() {
        return cars;
    }

    public void setCars(Collection<CarTO> cars) {
        this.cars = cars;
    }

    public static class EmployeeTOBuilder {

        private String firstName;
        private String lastName;
        private Date birthDay;
        private AddressTO address;
        private OfficeTO office;
        private PositionTO position;
        private Collection<CarTO> cars;

        public EmployeeTOBuilder() {
            super();
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

        public EmployeeTOBuilder withAddress(AddressTO address) {
            this.address = address;
            return this;
        }

        public EmployeeTOBuilder withOffice(OfficeTO office) {
            this.office = office;
            return this;
        }

        public EmployeeTOBuilder withPosition(PositionTO position) {
            this.position = position;
            return this;
        }

        public EmployeeTOBuilder withCars(Collection<CarTO> cars) {
            this.cars = cars;
            return this;
        }

        /*public AddressTOBuilder withId(long id) {
            this.id = id;
            return this;
        }*/

        public EmployeeTO build() {
            checkBeforeBuild(firstName, lastName, birthDay, address, office,position);
            return new EmployeeTO(firstName, lastName, birthDay, address, office,position);
        }

        private void checkBeforeBuild(String firstName, String lastName, Date birthDay, AddressTO address, OfficeTO office, PositionTO position) {
            if (firstName == null || lastName == null || birthDay == null || address == null || office == null || position == null) {
                throw new RuntimeException("Incorrect address to be created");
            }

        }
        private void checkBeforeBuild(String firstName, String lastName, Date birthDay, AddressTO address, OfficeTO office, PositionTO position, Collection<CarTO>cars) {
            if (firstName == null || lastName == null || birthDay == null || address == null || office == null || position == null || cars == null) {
                throw new RuntimeException("Incorrect address to be created");
            }

        }
    }
}
