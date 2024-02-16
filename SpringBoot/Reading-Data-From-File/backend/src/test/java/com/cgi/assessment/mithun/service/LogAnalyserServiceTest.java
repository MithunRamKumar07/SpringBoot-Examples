package com.cgi.assessment.mithun.service;

import com.cgi.assessment.mithun.exception.LogAnalyserException;
import com.cgi.assessment.mithun.model.LogAnalyserResponse;
import com.cgi.assessment.mithun.util.AppConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import static org.junit.jupiter.api.Assertions.*;

/** <p> Test class for {@link LogAnalyserService }</p>
 *
 */
@ExtendWith(MockitoExtension.class)
public class LogAnalyserServiceTest {

    @InjectMocks
    LogAnalyserService logAnalyserService;

    @BeforeEach
    public void setUp(){
        ReflectionTestUtils.setField(logAnalyserService,"logFilePath",AppConstants.LOG_FILE_PATH);
    }

    @Test
    public void testLogAnalyser() {
        LogAnalyserResponse logDetails = logAnalyserService.getLogsDetailsByType(AppConstants.DEBUG);
        assertNotNull(logDetails);
        assertTrue(logDetails.getLogDetails().size()>0);
        assertTrue(logDetails.getLogDetails().stream().anyMatch(log->log.getLogType().equals(AppConstants.DEBUG)));
    }

    @Test
    public void testLogAnalyserWithInvalidInput() {
        assertThrows(LogAnalyserException.class,()->logAnalyserService.getLogsDetailsByType(AppConstants.INVALID_INPUT));
    }
}
