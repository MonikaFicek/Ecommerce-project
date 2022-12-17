package Commerce.Data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class DataReader {
    public List<HashMap<String, String>> getJasonDataToMap() throws IOException {
    //read json to String
        String jasonContent=  FileUtils.readFileToString(new File((System.getProperty("user.dir") +"\\src\\test\\java\\Commerce\\Data\\PurchaseOrder.json")),
                StandardCharsets.UTF_8);

    //String to Hashmap Jackson Data Bind
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(jasonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        //{{map}.{map1}}

        return data;
    }
}
