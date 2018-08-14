package com.capgemini.types;

import java.math.BigInteger;
import java.time.Year;
import java.util.Collection;

public class CarTO {

	private String mark;
	private String model;
	private Year prodYear;
	private Double capacity;
	private Integer power;
	private BigInteger course;
	private ColorTO color;
	private TypeTO type;
	private Collection<EmployeeTO> keepers;
	private Long id;

	public CarTO(String mark, String model, Year prodYear, double capacity, int power, BigInteger course, ColorTO color, TypeTO type) {
		this.mark = mark;
		this.model = model;
		this.prodYear = prodYear;
		this.capacity = capacity;
		this.power = power;
		this.course = course;
		this.color = color;
		this.type = type;
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

	public BigInteger getCourse() {
		return course;
	}

	public ColorTO getColor() {
		return color;
	}

	public TypeTO getType() {
		return type;
	}

	public String getMark() {
		return mark;
	}

	public String getModel() {
		return model;
	}

	public Collection<EmployeeTO> getKeepers() {
		return keepers;
	}

	public static class CarTOBuilder {

		private String mark;
		private String model;
		private Year prodYear;
		private Double capacity;
		private Integer power;
		private BigInteger course;
		private ColorTO color;
		private TypeTO type;

		public CarTOBuilder() {
			super();
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

		public CarTOBuilder withCourse(BigInteger course) {
			this.course = course;
			return this;
		}

		public CarTOBuilder withCars(ColorTO color) {
			this.color = color;
			return this;
		}

		public CarTOBuilder withCars(TypeTO type) {
			this.type = type;
			return this;
		}

        /*public AddressTOBuilder withId(long id) {
            this.id = id;
            return this;
        }*/

		public CarTO build() {
			checkBeforeBuild(mark, model, prodYear, capacity, power, course, color, type);
			return new CarTO(mark, model, prodYear, capacity, power, course, color, type);
		}

		private void checkBeforeBuild(String mark, String model, Year prodYear, Double capacity, Integer power, BigInteger course, ColorTO color, TypeTO type) {
			if (mark == null || model == null || prodYear == null ||
				capacity == null || power == null || course == null ||
				color == null || type == null) {

					throw new RuntimeException("Incorrect car to be created");
			}

		}

	}

	@Override
	public String toString() {
		return "CarTo " + " id=" + id + "]"+ "[mark=" + mark + ", model=" + model + ", prodYear=" + prodYear +
				", capacity=" + capacity + ", power=" + power + ", color=" + color.getName() +
				", type=" + type.getName();
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
