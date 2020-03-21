package com.howtodoinjava.demo.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="terminal")
public class Terminal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "terminal_id")
    private Long id;

    @Column(name="Balance", nullable=false)
    private int Balance;

    @Column(name="Filial_owner", nullable=false)
	private String Filial_owner;

	@OneToMany(mappedBy = "terminal")
	private Collection<Operation> operations;


    public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public int  getBalance() {
		return Balance;
	}
	public void setBalance(int Number) {
		this.Balance = Balance;
	}

	public String getFilial_owner() {
		return Filial_owner;
	}
	public void   setFilial_owner(String Date_of) {
		this.Filial_owner = Filial_owner;
	}

	public Collection<Operation> getOperations() {
		return operations;
	}
	public void setOperations(Collection<Operation> operations) {
		this.operations = operations;
	}

    @Override
    public String toString() {
        return "CardEntity [id=" + id +
				", Balance=" + Balance +
                ", FW=" + Filial_owner +
				", Operation=" + operations.toString() +  "]";
    }
}