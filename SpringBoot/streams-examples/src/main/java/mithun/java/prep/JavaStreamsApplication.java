package mithun.java.prep;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class JavaStreamsApplication {

    public static void main(String[] args) {
       log.info("******* JAVA STREAMS EXAMPLES ******** ");
        StreamsExamples streamsExamples = new StreamsExamples();
//        streamsExamples.filterExamples();
//        streamsExamples.mapExamples();
//        streamsExamples.functionExample();
//        streamsExamples.streamSortingExamples();
//        streamsExamples.skipExample();
//        streamsExamples.reduceExample();
        streamsExamples.binaryOperatorExample();

    }
}
