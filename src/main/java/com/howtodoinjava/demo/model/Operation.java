package com.howtodoinjava.demo.model;

import javax.persistence.*;

@Entity
@Table(name="operation")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "operation_id")
    private Long id;

    @Column(name="Type", nullable=false)
    private String Type;

    @Column(name="Amount", nullable=false)
	private int Amount;

	@ManyToOne
	@JoinColumn(name = "terminal_id", referencedColumnName = "terminal_id", nullable = false)
	private Terminal terminal;

	//@ManyToOne
	//@JoinColumn(name = "from_id", referencedColumnName = "bill_id", nullable = false)
	@Column(name="Account_from")
	private String Account_from;

	//@ManyToOne
	//@JoinColumn(name = "account_to_id", referencedColumnName = "bill_id", nullable = false)
	@Column(name="Account_to", nullable=false)
	private String Account_to;

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String  getType() {
		return Type;
	}
	public void setType(String Type) {
		this.Type = Type;
	}

	public int  getAmount() {
		return Amount;
	}
	public void setAmount(int Amount) {
		this.Amount = Amount;
	}

	public Terminal getTerminal() {
		return terminal;
	}
	public void setTerminal(Terminal terminal) { this.terminal = terminal; }

	public String getAccount_from() { return Account_from; }
	public void setAccount_from(String Account_from) { this.Account_from = Account_from; }

	public String getAccount_to() { return Account_to; }
	public void setAccount_to(String Account_to) { this.Account_to = Account_to; }

    @Override
    public String toString() {
        return "CardEntity [id=" + id +
				", Type=" + Type +
                ", Amount=" + Amount +
				//", Account_from=" + Account_from   +
				//", Account_to=" + Account_to+
				", terminal=" + terminal.toString()+
				"]";
    }
}