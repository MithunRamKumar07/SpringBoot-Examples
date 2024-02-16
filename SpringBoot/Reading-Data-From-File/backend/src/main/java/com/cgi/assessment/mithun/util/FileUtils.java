package com.cgi.assessment.mithun.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**<p> Util class that reads the file from the class path</p>
 *
 */
@Slf4j
@Component
public class FileUtils {

    private static File recipeFile;
    private static File logFile;
    /**<p> Method that reads the log file from the path</p>
     * @param filePath path of the recipe json file that needs to be parsed
     * @return Parsed file content
     */
    public static File readRecipeFile(String filePath) {
        try {
            if(null==recipeFile){
                //File will be loaded only once and stay in the static memory till the app is restarted.
                //This will save the ResourceUtils.getFile call for every request.
                recipeFile = ResourceUtils.getFile(filePath);
                log.info("Loaded the Recipe json file successfully");
            }
        } catch (FileNotFoundException e) {
            //Do not throw the error
            log.error("Recipe Json file not found");
        }
        return recipeFile;
    }
    /**<p> Method that reads the log file from the path</p>
     * @param filePath path of the log file that needs to be parsed
     * @return Parsed file content
     */
    public static File readLogFile(String filePath) {
        try {
            //File will be loaded only once and stay in the static memory till the app is restarted.
            //This will save the ResourceUtils.getFile call for every request.
            logFile = ResourceUtils.getFile(filePath);
            log.info("Loaded the Log file successfully");
        } catch (FileNotFoundException e) {
            //Do not throw the error
            log.error("Log file not found");
        }
        return logFile;
    }
}
