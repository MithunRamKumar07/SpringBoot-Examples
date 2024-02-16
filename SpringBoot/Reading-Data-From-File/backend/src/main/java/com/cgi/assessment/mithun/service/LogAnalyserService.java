package com.cgi.assessment.mithun.service;

import com.cgi.assessment.mithun.exception.LogAnalyserException;
import com.cgi.assessment.mithun.model.LogAnalyserResponse;
import com.cgi.assessment.mithun.model.LogDetails;
import com.cgi.assessment.mithun.util.AppConstants;
import com.cgi.assessment.mithun.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
/**
 * <p> Service class to analyse the logs </p>
 * @author Mithun
 */
public class LogAnalyserService {

        @Value("${assessment.log.file.path}")
        String logFilePath;
    /** <p> Extracts the log statements from the input file</p>
     * @param logLevel from the user
     * @return The response list which has the logMessageDescriptions and their occurrences count
     * @throws IOException
     */
    public LogAnalyserResponse getLogsDetailsByType(String logLevel) throws LogAnalyserException {
        //Read the log statements from the file and filter them based on the input logLevel
        List<String> logMessages = extractLogsFromFile(logLevel);
        List<LogDetails> logDetails = new ArrayList<>();
        // Iterate the statements, count the number of occurrences
        logMessages.stream()
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .forEach((description,count)->{
                    buildResponse(logDetails, description,count,logLevel);
                });
        // Sort the log statements based on the descending occurrence count
        return new LogAnalyserResponse(logDetails.stream()
                .sorted(Comparator.comparingLong(LogDetails::getMessageOccurrenceCount).reversed())
                .toList());

    }

    /** <p> Extracts the log statements from the input file</p>
    * @param logLevel from the user
    * @return Log statements based on type
    * @throws IOException
    */
    private List<String> extractLogsFromFile(String logLevel) throws LogAnalyserException {
        try {
            File file = FileUtils.readLogFile(logFilePath);
            checkIfLogLevelIsValid(logLevel);
            return Files.lines(file.toPath())
                    .filter(line->line.contains(logLevel.toUpperCase()))
                    .map(line-> extractLogDescription(line))
                    .toList();
        } catch (IOException exception) {
            log.error("Exception occurred while extracting logs from the file. Exception Details: {}",exception.getMessage());
            throw new LogAnalyserException("Technical Error Occurred",
                    HttpStatus.INTERNAL_SERVER_ERROR,"Exception occurred while extracting logs from the file");
        }
    }
    /** <p> Checks the loglevel is in the allowed list</p>
     * @param logLevel loglevel from user
     * @return the message description from the file
     * @throws IOException
     */
    private void checkIfLogLevelIsValid(String logLevel) {
        if(!AppConstants.ALLOWED_LOG_LEVELS.contains(logLevel)){
            log.error("The input loglevel is not in the allowed list");
            throw new LogAnalyserException("Please enter a valid loglevel",
                    HttpStatus.BAD_REQUEST,"Your input is not allowed");
        }
    }

    /** <p> Extracts the log statements from the input file</p>
     * @param line Each line of the log fine
     * @return the message description from the file
     * @throws IOException
     */
    private static String extractLogDescription(String line) {
        return line.substring(line.indexOf("]")+1);
    }

    /** <p> Builds the response list which contains the logs statements and the no of occurrences</p>
     * @param logDetails Each line of the log fine
     * @param description messageDescription
     * @param count Number of times the description has occured in the file
     * @param logLevel Input loglevel from the user
     *
     */
    private static void buildResponse(List<LogDetails> logDetails, String description, Long count,String logLevel) {
        LogDetails message = new LogDetails();
        message.setLogType(logLevel);
        message.setMessageDescription(description);
        message.setMessageOccurrenceCount(count);
        logDetails.add(message);
    }
}
