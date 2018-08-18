package com.capgemini.types;

import java.util.List;

public class OfficeTO {
    private Long id;
    private String name;
    private String phoneNumber;
    private long address;
    private List<Long> loans;

    public OfficeTO(Long id, String name, String phoneNumber, Long address, List<Long> loans) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.loans = loans;
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

    public Long getAddress() {
        return address;
    }


    public static class OfficeTOBuilder {

        private Long id;
        private String name;
        private String phoneNumber;
        private Long address;
        private List<Long> loans;

        public OfficeTOBuilder() {
            super();
        }

        public OfficeTOBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public OfficeTOBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public OfficeTOBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public OfficeTOBuilder withAddress(Long address) {
            this.address = address;
            return this;
        }

        public OfficeTOBuilder withLoans(List<Long> loans) {
            this.loans = loans;
            return this;
        }


        public OfficeTO build() {
            checkBeforeBuild(name, phoneNumber, address);
            return new OfficeTO(id, name, phoneNumber, address, loans);
        }

        private void checkBeforeBuild(String name, String phoneNumber, Long address) {
            if (name == null || phoneNumber == null || address == null) {
                throw new RuntimeException("Incorrect office to be created");
            }

        }

    }
}
