package com.capgemini.types;

public class TypeTO {

    private Long id;
    private String name;

    public TypeTO(String name) {
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
    
    public static class TypeToBuilder {

        private Long id;
        private String name;


        public TypeToBuilder() {
            super();
        }

        public TypeToBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public TypeToBuilder withName(String name) {
            this.name = name;
            return this;
        }


        public TypeTO build() {
            checkBeforeBuild(name);
            return new TypeTO(name);
        }

        private void checkBeforeBuild(String name) {
            if (name == null) {
                throw new RuntimeException("Incorrect type be created");
            }
        }
    }

    public int hashCode() {
        final int prime = 37;
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
        TypeTO other = (TypeTO) obj;
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
