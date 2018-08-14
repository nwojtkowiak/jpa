package com.capgemini.domain;

import com.capgemini.listeners.InsertListener;
import com.capgemini.listeners.UpdateListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "EMPLOYER")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({UpdateListener.class, InsertListener.class})
public class EmployeeEntity extends  AbstractEntity  implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, length = 45)
	private String firstName;
	@Column(nullable = false, length = 45)
	private String lastName;
	@Column(nullable = false)
	//@Temporal(TemporalType.TIMESTAMP)
	private Date birthDay;

	@ManyToOne
	@JoinColumn(name = "address_id", nullable = false)
	private AddressEntity address;

	@ManyToOne
	@JoinColumn(name = "office_id", nullable = false)
	private OfficeEntity office;

	@ManyToOne
	@JoinColumn(name = "position_id", nullable = false)
	private PositionEntity position;


	@ManyToMany //co ustawić w usuwaniu?
	@JoinTable(name = "KEEPER", joinColumns = {@JoinColumn(name = "employee_id")},
	inverseJoinColumns = {@JoinColumn(name = "car_id")})
	private Collection<CarEntity> cars;



	// for hibernate
	public EmployeeEntity() {
	}

	public EmployeeEntity(String firstName, String lastName, Date birthDay, AddressEntity address, OfficeEntity office,
						  PositionEntity position) {
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

	public String getLastName() {
		return lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public OfficeEntity getOffice() {
		return office;
	}

	public PositionEntity getPosition() {
		return position;
	}
}
