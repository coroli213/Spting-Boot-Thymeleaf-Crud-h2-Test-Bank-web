package com.howtodoinjava.demo.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "client_id")
    private Long id;
    
    @Column(name="first_name")
    private String firstName;
    
    @Column(name="second_name")
	private String secondName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="serial")
	private String serial;

	@Column(name="Pnumber")
	private String number;

	@OneToMany(mappedBy = "client")
	private Collection<Card> cards;


    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	public Collection<Card> getCards() {
		return cards;
	}
	public void setCards(Collection<Card> cards) {
		this.cards = cards;
	}

	@Override
	public String toString() {
		return "CardEntity [id=" + id +
				", Type=" + firstName +
				", Amount=" + secondName +
				", Account_from=" + lastName +
				", Account_to=" + serial +
				", terminal=" + number +
				", Amount=" + cards.toString() +
				"]";
	}
}