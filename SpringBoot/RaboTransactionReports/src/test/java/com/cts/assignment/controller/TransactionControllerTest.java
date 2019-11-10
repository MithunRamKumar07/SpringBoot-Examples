package com.cts.assignment.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import com.cts.assignment.exception.RaboBankException;
import com.cts.assignment.model.Transactions;
import com.cts.assignment.service.TransactionService;
import com.cts.assignment.utils.Constants;

/**
 * <p>
 * Test for {@link TransactionController.java }
 * </p>
 * 
 * @author Mithun
 *
 */
@RunWith(SpringRunner.class)
public class TransactionControllerTest {

	@InjectMocks
	TransactionController transactionController;

	@Mock
	private TransactionService transactionReportService;

	@Mock
	MultipartFile multipartFile;

	@Test
	public void initiateTransaction_withInValidCsvData_WillProcessAndReturnReport() throws Exception {

		when(transactionReportService.evaluateTransaction(Matchers.<Transactions>any())).thenReturn(new HashMap<>());
		when(multipartFile.isEmpty()).thenReturn(false);
		when(multipartFile.getBytes()).thenReturn(Constants.CSV_INPUT.getBytes());
		when(multipartFile.getOriginalFilename()).thenReturn("file.csv");
		assertNotNull(transactionController.singleFileUpload(multipartFile));
	}

	@Test
	public void initiateTransaction_withInValidXmlData_willProcessAndReturnReport()
			throws RaboBankException, Exception {

		MultipartFile multipartXmlFile = getMultiPartXmlFile();

		when(transactionReportService.evaluateTransaction(Matchers.<Transactions>any())).thenReturn(new HashMap<>());
		assertNotNull(transactionController.singleFileUpload(multipartXmlFile));
	}

	@Test
	public void initiateTransaction_withInValidCsvData_willReturnErrorReport() throws Exception {

		when(transactionReportService.evaluateTransaction(Matchers.<Transactions>any())).thenReturn(new HashMap<>());
		when(multipartFile.isEmpty()).thenReturn(false);
		when(multipartFile.getBytes()).thenReturn("InvalidString".getBytes());
		when(multipartFile.getOriginalFilename()).thenReturn("file.csv");
		assertNotNull(transactionController.singleFileUpload(multipartFile));
	}

	@Test
	public void initiateTransaction_withEmptyCsvData_willProcessAndReturnErrorReport() throws Exception {

		when(transactionReportService.evaluateTransaction(Matchers.<Transactions>any())).thenReturn(new HashMap<>());
		when(multipartFile.isEmpty()).thenReturn(true);
		assertNotNull(transactionController.singleFileUpload(multipartFile));
	}

	@Test(expected = RaboBankException.class)
	public void test_withInValidTransactions_shouldThrowException() throws Exception {

		when(transactionReportService.evaluateTransaction(Matchers.<Transactions>any()))
				.thenThrow(new RaboBankException());
		transactionController.singleFileUpload(multipartFile);
	}
	
	private MultipartFile getMultiPartXmlFile() throws FileNotFoundException, IOException {
		
		String fileName = "records.xml";
		ClassLoader classLoader = new TransactionControllerTest().getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		String name = Constants.FILE_NAME;
		String originalFileName = Constants.FILE_NAME;
		String contentType = Constants.CONTENT_TYPE;
		byte[] content = null;
		content = Files.readAllBytes(file.toPath());
		MultipartFile result = new MockMultipartFile(name, originalFileName, contentType, content);
		return result;
	}
}