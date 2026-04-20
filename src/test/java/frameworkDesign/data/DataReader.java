package frameworkDesign.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

//Note: this method exists already in BaseTest - Duplicate here
public class DataReader {// one time utility for framework

    public List<HashMap<String, String>> getJsonDataToMap() throws IOException {

        // Read Json to String
        String jsonContent = FileUtils.readFileToString(new File(
                System.getProperty("user.dir") + "\\src\\test\\java\\frameworkDesign\\data\\PurchaseOrder.json"),
                StandardCharsets.UTF_8);

        // String to HashMap - get dependency - jackson databind
        ObjectMapper mapper = new ObjectMapper(); // to call.readValue method
        List<HashMap<String, String>> data = mapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {
                });
        return data;// data inlcludes list of HashMaps

    }

}

/*
 * Since readFileToString(FIle file) is deprecated, so use
 * readFileToString(File, Charset) instead. This should solve the issue.
 *
 * String jsonContent =FileUtils.readFileToString(new
 * File(System.getProperty("user.dir")+
 * "\\src\\test\\java\\Vrishank_Mishra_SFD\\data\\PurchaseOrder.json"),
 * StandardCharsets.UTF_8);
 */