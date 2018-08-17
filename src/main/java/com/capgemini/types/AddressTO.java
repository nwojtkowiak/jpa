package com.capgemini.types;

public class AddressTO {
    private Long id;
    private String street;
    private int building;
    private int flat;
    private String post_code;
    private String city;

    public Long getId() {
        return id;
    }

    public AddressTO(String street, int building, int flat, String post_code, String city) {
        this.street = street;
        this.building = building;
        this.flat = flat;
        this.post_code = post_code;
        this.city = city;
    }

    public AddressTO(Long id, String street, int building, int flat, String post_code, String city) {
        this.id = id;
        this.street = street;
        this.building = building;
        this.flat = flat;
        this.post_code = post_code;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getBuilding() {
        return building;
    }

    public void setBuilding(int building) {
        this.building = building;
    }

    public int getFlat() {
        return flat;
    }

    public void setFlat(int flat) {
        this.flat = flat;
    }

    public String getPost_code() {
        return post_code;
    }

    public void setPost_code(String post_code) {
        this.post_code = post_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public static class AddressTOBuilder {

        private Long id;
        private String street;
        private int building;
        private int flat;
        private String post_code;
        private String city;

        public AddressTOBuilder() {
            super();
        }

        public AddressTOBuilder withStreet(String street) {
            this.street = street;
            return this;
        }

        public AddressTOBuilder withBuilding(int building) {
            this.building = building;
            return this;
        }

        public AddressTOBuilder withFlat(int flat) {
            this.flat = flat;
            return this;
        }

        public AddressTOBuilder withPostCode(String post_code) {
            this.post_code = post_code;
            return this;
        }

        public AddressTOBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public AddressTOBuilder withId(long id) {
            this.id = id;
            return this;
        }

        public AddressTO build() {
            checkBeforeBuild(street, building, flat, post_code, city);
            return new AddressTO(id,street,building,flat,post_code,city);
        }

        private void checkBeforeBuild(String street, Integer building, Integer flat, String post_code, String city) {
            if (street == null || building == null || flat == null || post_code == null || city == null) {
                throw new RuntimeException("Incorrect address to be created");
            }

        }
    }


}
