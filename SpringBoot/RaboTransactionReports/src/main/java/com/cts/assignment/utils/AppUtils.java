package com.cts.assignment.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import com.cts.assignment.model.Transaction;
import com.cts.assignment.model.Transactions;

/**
 * <p>Util method used for the reusable logic across the application</p>
 * @author Mithun
 */
public class AppUtils {

	/** <p>Method that converts the input XMl file to Java Object</p>
	 * @param input XML file to be parsed 
	 * @param clazz to which the XML doc is converted
	 * @return parsed Object which can be typecasted to specific objects based on need.
	 * @throws JAXBException
	 */
	public static <T> Object parseXml(StringReader fileContent, Class<T> clazz) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		return jaxbUnmarshaller.unmarshal(fileContent);
	}

	/** <p>Method that converts the input CSV file to Java Object</p>
	 * @param input CSV file to be parsed 
	 * @return the parsed Java Object {@link Transactions}
	 * @throws IOException
	 */
	public static Transactions parseCSV(StringReader inputData) throws IOException {

		Transactions transactions = new Transactions();

		try (CSVParser csvParser = new CSVParser(inputData,
				CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			List<Transaction> transactionRecords = new ArrayList<>();
			csvParser.forEach(csvRecord -> transactionRecords.add(new Transaction(csvRecord.get("Reference"),
					csvRecord.get("AccountNumber"), csvRecord.get("Description"), csvRecord.get("Mutation"),
					csvRecord.get("End Balance"), csvRecord.get("Start Balance"))));
			
			transactions.setTransactions(transactionRecords);
		}
		return transactions;
	}
}
