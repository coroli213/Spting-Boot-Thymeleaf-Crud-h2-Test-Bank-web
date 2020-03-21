package com.howtodoinjava.demo.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bill_id")
    private Long id;

    @Column(name="Balance", nullable=false)
    private int Balance;

    @Column(name="Bank", nullable=false)
	private String Bank_owner;

	@OneToMany(mappedBy = "bill")
	private Collection<Card> cards;

	//@OneToMany(mappedBy = "bill")
	//private Collection<Operation> operations;
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public int  getBalance() {
		return Balance;
	}
	public void setBalance(int Number) {
		this.Balance = Balance;
	}

	public String getBank_owner() {
		return Bank_owner;
	}
	public void   setBank_owner(String Date_of) {
		this.Bank_owner = Bank_owner;
	}

	public Collection<Card> getCards() {
		return cards;
	}
	public void setCards(Collection<Card> cards) {
		this.cards = cards;
	}
//
//	public Collection<Operation> getOperation() { return operations; }
//	public void setOperations(Collection<Operation> operations) { this.operations = operations; }
//
    @Override
    public String toString() {
        return "CardEntity [id=" + id +
				", Number=" + Balance +
                ", Date of=" + Bank_owner +
				", Pin=" + cards.toString()   +
				//", Csv=" + operations+
				"]";
    }
}