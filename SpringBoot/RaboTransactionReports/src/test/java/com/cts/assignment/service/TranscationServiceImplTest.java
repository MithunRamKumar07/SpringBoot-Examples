package com.cts.assignment.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.assignment.exception.RaboBankException;
import com.cts.assignment.model.Transaction;
import com.cts.assignment.model.Transactions;

/** <p> Test for {@link TransactionServiceImpl.java }</p>
 * @author Mithun
 *
 */
@RunWith(SpringRunner.class)
public class TranscationServiceImplTest {

	@InjectMocks
	TranscationServiceImpl transcationReportServiceImpl;

	@Test
	public void test_withInValidTransactions_willReturnInValidTransactionReport() throws Exception {

		Transactions transactions = new Transactions();
		List<Transaction> transaction = new ArrayList<Transaction>();
		transaction.add(new Transaction("123", "accountNumber", "description", "5", "15", "10"));
		transaction.add(new Transaction("123", "accountNumber", "description", "5", "15", "10"));
		transactions.setTransactions(transaction);

		Map<String, ArrayList<Transaction>> result = transcationReportServiceImpl.evaluateTransaction(transactions); 

		assertNotNull(result);
	}

	@Test
	public void test_withValidTransactions_willReturnTransactionReport() throws Exception {

		Transactions records = getTransactionRecords();
		Map<String, ArrayList<Transaction>> result = transcationReportServiceImpl.evaluateTransaction(records);
		
		assertNotNull(result);
	}

	@Test(expected = RaboBankException.class)
	public void test_withInValidTransactions_shouldThrowException() throws Exception {
		when(transcationReportServiceImpl.evaluateTransaction(any())).thenThrow(new RaboBankException());
		transcationReportServiceImpl.evaluateTransaction(getTransactionRecords());
	}
	
	private Transactions getTransactionRecords() {
		Transactions records = new Transactions();
		Transaction transaction = new Transaction();
		transaction.setAccountNumber("accountNumber");
		transaction.setReference("121");
		transaction.setDescription("description");
		transaction.setMutation("5");
		transaction.setStartBalance("10");
		transaction.setEndBalance("15");
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);
		records.setTransactions(transactions);
		return records;
	}
}