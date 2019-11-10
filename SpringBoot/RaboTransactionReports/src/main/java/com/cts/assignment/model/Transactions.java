package com.cts.assignment.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**<p> POJO for the records tag in the input xml/csv</p>
 * @author Mithun
 *
 */
@XmlRootElement(name = "records")
@XmlAccessorType(XmlAccessType.FIELD)
public class Transactions implements Serializable{
	
	private static final long serialVersionUID = 5887437724431563713L;
	
	@XmlElement(name = "record")
    private List<Transaction> transactions;

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transactions() {
	}
	
	

}
