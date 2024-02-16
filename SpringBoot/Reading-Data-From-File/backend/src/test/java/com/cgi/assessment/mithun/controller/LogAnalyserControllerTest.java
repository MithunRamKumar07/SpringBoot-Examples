package com.cgi.assessment.mithun.controller;

import com.cgi.assessment.mithun.model.LogAnalyserResponse;
import com.cgi.assessment.mithun.service.LogAnalyserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/** <p> Test class for {@link LogAnalyserController }</p>
 *
 */
@ExtendWith(MockitoExtension.class)
public class LogAnalyserControllerTest {

    @InjectMocks
    LogAnalyserController logAnalyserController;

    @Mock
    LogAnalyserService logAnalyserService;

    @Mock
    LogAnalyserResponse logAnalyserResponse;

    @Test
    public void testLogAnalyser(){
        when(logAnalyserService.getLogsDetailsByType("DEBUG")).thenReturn(logAnalyserResponse);
        assertNotNull(logAnalyserController.analyseLogs("DEBUG"));
    }

}
