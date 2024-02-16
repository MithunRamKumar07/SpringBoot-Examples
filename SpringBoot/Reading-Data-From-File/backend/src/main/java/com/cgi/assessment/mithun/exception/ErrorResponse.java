package com.cgi.assessment.mithun.exception;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Response schema for Error Scenario
 */
@Slf4j
@Data
public class ErrorResponse {

    String errorDetails;
    String additionalInformation;
}
