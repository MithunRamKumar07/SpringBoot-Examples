package com.cts.assignment.model;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Component;

/** The response object that has the transaction objects
 * 
 * @author Mithun
 *
 */
@Component
public class TransactionReportVO {
	
	private Map<String, ArrayList<Transaction>> transactionResults;

	public Map<String, ArrayList<Transaction>> getTransactionResults() {
		return transactionResults;
	}

	public void setTransactionResults(Map<String, ArrayList<Transaction>> transactionResults) {
		this.transactionResults = transactionResults;
	}

	@Override
	public String toString() {
		return "TransactionReport [transactionResults=" + transactionResults + "]";
	} 

}
