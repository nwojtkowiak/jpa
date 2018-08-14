package com.capgemini.types;

import com.capgemini.domain.AddressEntity;

public class OfficeTO {
    private Long id;
    private String name;
    private String phoneNumber;
    private AddressTO address;

    public OfficeTO(String name, String phoneNumber, AddressTO address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AddressTO getAddress() {
        return address;
    }

    public void setAddress(AddressTO address) {
        this.address = address;
    }

    public static class OfficeTOBuilder {

        private String name;
        private String phoneNumber;
        private AddressTO address;

        public OfficeTOBuilder() {
            super();
        }

        public OfficeTOBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public OfficeTOBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public OfficeTOBuilder withAddress(AddressTO address) {
            this.address = address;
            return this;
        }



        /*public AddressTOBuilder withId(long id) {
            this.id = id;
            return this;
        }*/

        public OfficeTO build() {
            checkBeforeBuild(name, phoneNumber, address);
            return new OfficeTO(name, phoneNumber, address);
        }

        private void checkBeforeBuild(String name, String phoneNumber, AddressTO address) {
            if (name == null || phoneNumber == null || address == null) {
                throw new RuntimeException("Incorrect office to be created");
            }

        }

    }
}
