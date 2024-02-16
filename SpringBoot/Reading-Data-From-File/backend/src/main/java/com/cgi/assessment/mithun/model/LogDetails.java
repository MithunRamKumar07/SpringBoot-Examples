package com.cgi.assessment.mithun.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LogDetails {

    String logType;
    String messageDescription;
    long messageOccurrenceCount;
}

