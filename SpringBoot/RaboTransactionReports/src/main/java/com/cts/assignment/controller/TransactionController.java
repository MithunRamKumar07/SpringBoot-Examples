package com.cts.assignment.controller;

import java.io.StringReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cts.assignment.exception.RaboBankException;
import com.cts.assignment.model.TransactionReportVO;
import com.cts.assignment.model.Transactions;
import com.cts.assignment.service.TransactionService;
import com.cts.assignment.utils.AppUtils;

/**
 *<p> Controller that handles the requests into the application </p>
 *
 *@author Mithun
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	TransactionService transactionService;
	private static Logger logger = LoggerFactory.getLogger(TransactionController.class);

	/** Method that routes the requests to the service layer to generate transaction reports
	 * @param Multipart csv/xml file for which the transaction reports are generated
	 * @return the transaction report {@link TransactionReportVO}
	 * @throws RaboBankException
	 */
	@RequestMapping(value = "/report", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<TransactionReportVO> singleFileUpload(@RequestParam("file") MultipartFile file)
			throws RaboBankException {

		TransactionReportVO response = new TransactionReportVO();
		
		if (file.isEmpty()) {
			logger.info("File passed is empty.Please pass a valid File");
			return new ResponseEntity<TransactionReportVO>(HttpStatus.BAD_REQUEST);
		}
		try {
			StringReader fileContent = new StringReader(new String(file.getBytes()));
			Transactions transactions = null;
			
			if (file.getOriginalFilename().endsWith(".xml")) {
				transactions = (Transactions) AppUtils.parseXml(fileContent, Transactions.class);
			} else {
				transactions = (Transactions) AppUtils.parseCSV(fileContent);
			}
			
			logger.info("Transaction Records : {}", transactions.getTransactions());
			response.setTransactionResults(transactionService.evaluateTransaction(transactions));
			logger.info("Transaction Sucessfully Completed!! ");
		} catch (Exception e) {
			logger.error("Exception occurred in controller layer while evaluating Transactions :{}", e.getMessage());
			throw new RaboBankException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
					"Exception occurred while evaluating Transactions");
		}
		return new ResponseEntity<TransactionReportVO>(response, HttpStatus.OK);
	}
}
