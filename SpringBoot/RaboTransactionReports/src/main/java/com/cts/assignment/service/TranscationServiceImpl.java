 package com.cts.assignment.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cts.assignment.exception.RaboBankException;
import com.cts.assignment.model.Transaction;
import com.cts.assignment.model.Transactions;

/**
 * <p>Service class that has the business logic to evaluate the transactions and return a JSON report</p>
 * @author Mithun
 *
 */
@Service
public class TranscationServiceImpl implements TransactionService {

	private static Logger logger = LoggerFactory.getLogger(TranscationServiceImpl.class);

	/** <p> This method evaluates the transactions based on the endBalance and unique reference number</p>
	 * 
	 * <p>A transaction is considered to be valid if :</p>
	 * <li> The reference number is unique</li>
	 * <li> endBalance = startBalance + Mutation</li>
	 * 
	 * <p> If there are any discrepancies in the above criteria, the transaction is considered as Invalid
	 * 
	 * @param List of transactions from the input CSV/XML file
	 * @return List of Valid and Invalid Transactions in key value format
	 * @throws {@link RaboBankException.java}
	 *
	 */
	@Override
	public Map<String, ArrayList<Transaction>> evaluateTransaction(Transactions transactionRecords)
			throws RaboBankException {

		Map<String, ArrayList<Transaction>> result = new HashMap<>();
		ArrayList<Transaction> validRecords = new ArrayList<>();
		ArrayList<Transaction> inValidRecords = new ArrayList<>();
		try {
			List<Transaction> transactions = transactionRecords.getTransactions();
			if (transactions != null && !transactions.isEmpty()) {
				transactions.forEach(record -> {

					double endBalance = Double.parseDouble(record.getStartBalance())
							+ Double.parseDouble(record.getMutation());
					endBalance = BigDecimal.valueOf(endBalance).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					boolean isReferenceExists = validRecords.stream()
							.anyMatch(validRecord -> validRecord.getReference().equals(record.getReference()));

					if (endBalance == Double.parseDouble(record.getEndBalance()) && !isReferenceExists) {
						validRecords.add(record);
					} else {
						inValidRecords.add(record);
					}
				});
				result.put("validTransactions", validRecords);
				logger.info("valid Transactions: " + validRecords);
				result.put("inValidTransactions", inValidRecords);
				logger.info("invalid Transactions: " + validRecords);
			}
		} catch (Exception e) {
			logger.error("Exception occurred in service layer while evaluating Transactions :" + e.getMessage());
			throw new RaboBankException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
					"Exception occurred while evaluating Transactions");
		}
		return result;
	}
}
