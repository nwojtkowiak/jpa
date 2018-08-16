package com.capgemini.types;

public class ColorTO {
    private Long id;
    private String name;

    public ColorTO(Long id, String name){
        this.id = id;
        this.name = name;
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

    public static class ColorToBuilder {

        private Long id;
        private String name;


        public ColorToBuilder() {
            super();
        }

        public ColorToBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public ColorToBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ColorTO build() {
            checkBeforeBuild(name);
            return new ColorTO(id,name);
        }

        private void checkBeforeBuild(String name) {
            if (name == null ) {
                throw new RuntimeException("Incorrect color be created");
            }
        }
    }

    @Override
    public int hashCode() {
        final int prime = 35;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ColorTO other = (ColorTO) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;

        return true;
    }


}
