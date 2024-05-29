package LearnSelnium.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public List<HashMap<String, String>>  getJsonDataToHashMap() throws IOException
	{
		////scan json data to convert to String varaible
	
	String jsonContent=	FileUtils.readFileToString(new File(System.getProperty("user.dir") +
			"/Seleniume2e/src/test/java/LearnSelnium/data/data.json"),StandardCharsets.UTF_8);
	//we need to pass encoding format StandardCharsets.UTF_8 as a second argument
		
		//String to HashMap
		ObjectMapper obj =new ObjectMapper();
		
		
		//we have two arrays of list{{""},{""}},
		//that we need to convert into Hashmap that will be added in the lists list{hashmap1},{hashmap2}
		List<HashMap<String, String>> data=obj.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
		return data;

}
}