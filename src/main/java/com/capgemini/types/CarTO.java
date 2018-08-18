package com.capgemini.types;

import java.time.Year;
import java.util.List;

public class CarTO {

    private Long id;
    private String mark;
    private String model;
    private Year prodYear;
    private Double capacity;
    private Integer power;
    private Long course;
    private Long color;
    private Long type;
    private List<Long> keepers;
    private List<Long> loans;


    public CarTO(Long id, String mark, String model, Year prodYear,
                 Double capacity, Integer power, Long course,
                 Long color, Long type, List<Long> keepers, List<Long> loans) {
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.prodYear = prodYear;
        this.capacity = capacity;
        this.power = power;
        this.course = course;
        this.color = color;
        this.type = type;
        this.keepers = keepers;
        this.loans = loans;

    }

    public Long getId() {
        return id;
    }

    public Year getProdYear() {
        return prodYear;
    }

    public double getCapacity() {
        return capacity;
    }

    public int getPower() {
        return power;
    }

    public long getCourse() {
        return course;
    }

    public Long getColor() {
        return color;
    }

    public Long getType() {
        return type;
    }

    public String getMark() {
        return mark;
    }

    public String getModel() {
        return model;
    }

    public List<Long> getLoans() {
        return loans;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public static class CarTOBuilder {

        private Long id;
        private String mark;
        private String model;
        private Year prodYear;
        private Double capacity;
        private Integer power;
        private Long course;
        private Long color;
        private Long type;
        private List<Long> keepers;
        private List<Long> loans;

        public CarTOBuilder() {
            super();
        }

        public CarTOBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public CarTOBuilder withMark(String mark) {
            this.mark = mark;
            return this;
        }

        public CarTOBuilder withModel(String model) {
            this.model = model;
            return this;
        }

        public CarTOBuilder withProdYear(Year prodYear) {
            this.prodYear = prodYear;
            return this;
        }

        public CarTOBuilder withCapacity(Double capacity) {
            this.capacity = capacity;
            return this;
        }

        public CarTOBuilder withPower(Integer power) {
            this.power = power;
            return this;
        }

        public CarTOBuilder withCourse(long course) {
            this.course = course;
            return this;
        }

        public CarTOBuilder withColor(Long color) {
            this.color = color;
            return this;
        }

        public CarTOBuilder withType(Long type) {
            this.type = type;
            return this;
        }

        public CarTOBuilder withLoans(List<Long> loans) {
            this.loans = loans;
            return this;
        }


        public CarTO build() {
            checkBeforeBuild(mark, model, prodYear, capacity, power, course, color, type);
            return new CarTO(id, mark, model, prodYear, capacity, power, course, color, type, keepers,loans);
        }

        private void checkBeforeBuild(String mark, String model, Year prodYear,
                                      Double capacity, Integer power, Long course,
                                      Long color, Long type) {
            if (mark == null || model == null || prodYear == null ||
                    capacity == null || power == null || course == null ||
                    color == null || type == null) {

                throw new RuntimeException("Incorrect car to be created");
            }

        }

    }

    @Override
    public String toString() {
        return "CarTo " + " id=" + id + "]" + "[mark=" + mark + ", model=" + model + ", prodYear=" + prodYear +
                ", capacity=" + capacity + ", power=" + power + ", color=" + color +
                ", type=" + type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mark == null) ? 0 : mark.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((model == null) ? 0 : model.hashCode());
        result = prime * result + ((prodYear == null) ? 0 : prodYear.hashCode());
        result = prime * result + ((capacity == null) ? 0 : capacity.hashCode());
        result = prime * result + ((power == null) ? 0 : power.hashCode());
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        CarTO other = (CarTO) obj;

        if (mark == null) {
            if (other.mark != null)
                return false;
        } else if (!mark.equals(other.mark))
            return false;

		/*if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;*/

        if (model == null) {
            if (other.model != null)
                return false;
        } else if (!model.equals(other.model))
            return false;

        if (color == null) {
            if (other.color != null)
                return false;
        } else if (!color.equals(other.color))
            return false;

        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;

        if (power == null) {
            if (other.power != null)
                return false;
        } else if (!power.equals(other.power))
            return false;

        if (capacity == null) {
            if (other.capacity != null)
                return false;
        } else if (!capacity.equals(other.capacity))
            return false;

        if (course == null) {
            if (other.course != null)
                return false;
        } else if (!course.equals(other.course))
            return false;

        if (prodYear == null) {
            if (other.prodYear != null)
                return false;
        } else if (!prodYear.equals(other.prodYear))
            return false;
        return true;
    }

}
