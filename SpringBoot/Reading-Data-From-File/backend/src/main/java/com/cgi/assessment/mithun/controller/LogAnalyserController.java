package com.cgi.assessment.mithun.controller;

import com.cgi.assessment.mithun.exception.LogAnalyserException;
import com.cgi.assessment.mithun.model.LogAnalyserResponse;
import com.cgi.assessment.mithun.service.LogAnalyserService;
import com.cgi.assessment.mithun.util.AppConstants;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**<p> Controller class that has the endpoint to analyse logs based on user input</p>
 */
@Slf4j
@RestController
@RequestMapping(value = AppConstants.LOG_ANALYSER_PATH)
@Validated
public class LogAnalyserController {

    @Autowired
    LogAnalyserService logAnalyserService;

    /**<p> Method to analyse the logs</p>
     * @return List of LogDetails objects
     * @throws LogAnalyserException
     */
    @GetMapping(value = AppConstants.ANALYSE_PATH,produces=AppConstants.APPLICATION_JSON)
    public ResponseEntity<LogAnalyserResponse>analyseLogs(@Valid @PathVariable String logLevel) throws LogAnalyserException {
        log.info("Analysing the logs based on the input logLevel");
        LogAnalyserResponse response = logAnalyserService.getLogsDetailsByType(logLevel);
        log.info("Successfully analysed the logs");
        return ResponseEntity.ok()
                .body(response);
    }
}
