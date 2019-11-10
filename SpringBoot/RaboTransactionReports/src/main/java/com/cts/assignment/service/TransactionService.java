package com.cts.assignment.service;

import java.util.ArrayList;
import java.util.Map;

import com.cts.assignment.exception.RaboBankException;
import com.cts.assignment.model.Transaction;
import com.cts.assignment.model.Transactions;

public interface TransactionService {

	public Map<String, ArrayList<Transaction>> evaluateTransaction(Transactions transactionRecords) throws RaboBankException;

}
