package com.capgemini.types;

public class PositionTO {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public PositionTO(String name) {
        this.name = name;
    }

    public PositionTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class PositionToBuilder {

        private Long id;
        private String name;


        public PositionToBuilder() {
            super();
        }

        public PositionToBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public PositionToBuilder withName(String name) {
            this.name = name;
            return this;
        }



        public PositionTO build() {
            checkBeforeBuild(name);
            return new PositionTO(id,name);
        }

        private void checkBeforeBuild(String name) {
            if (name == null ) {
                throw new RuntimeException("Incorrect position be created");
            }
        }
    }
}
