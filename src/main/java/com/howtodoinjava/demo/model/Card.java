package com.howtodoinjava.demo.model;

import javax.persistence.*;


@Entity
@Table(name="card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "card_id")
    private Long id;

    @Column(name="CNumber", nullable=false)
    private String Number;

    @Column(name="Date_of", nullable=false)
	private String Date_of;

	@Column(name="Pin", nullable=false)
	private String Pin;

	@Column(name="CSV", nullable=false)
	private String Csv;

	@ManyToOne
	@JoinColumn(name = "client_id", referencedColumnName = "client_id", nullable = false)
	private Client client;

	@ManyToOne
	@JoinColumn(name = "bill_id", referencedColumnName = "bill_id", nullable = false)
	private Bill bill;
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return Number;
	}
	public void   setNumber(String Number) {
		this.Number = Number;
	}

	public String getDate_of() {
		return Date_of;
	}
	public void   setDate_of(String Date_of) {
		this.Date_of = Date_of;
	}

	public String getPin() {
		return Pin;
	}
	public void setPin(String Pin) {
		this.Pin = Pin;
	}

	public String getCsv() {
		return Csv;
	}
	public void   setCsv(String Csv) {
		this.Csv = Csv;
	}

	public Client getClient() {
		return client;
	}
	public void   setClient(Client client) {
		this.client = client;
	}

	public Bill getBill() {
		return bill;
	}
	public void setBill(Bill bill) {
		this.bill = bill;
	}

    @Override
    public String toString() {
        return "CardEntity [id=" + id +
				", Number=" + Number +
                ", Date of=" + Date_of +
				", Pin=" + Pin   +
				", Csv=" + Csv+  "]";
    }
}