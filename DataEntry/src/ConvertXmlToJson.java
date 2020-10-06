import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONObject;
import org.json.XML;

public class ConvertXmlToJson {

	public static void main(String[] args) {
		String patientXmlFile = "C:\\Dev\\AMx\\DataEntry\\src\\convertedFormatPatientInfo.json";
		try {
			File file = new File ("C:\\Dev\\AMx\\DataEntry\\src\\patientInfo.xml");
			InputStream inputStream = new FileInputStream (file);
			StringBuilder sb = new StringBuilder();
			int ptr = 0;
			while ((ptr = inputStream.read()) != -1) {
				sb.append((char) ptr);
				//System.out.println(ptr);
			}
			
			String xml = sb.toString();
			System.out.println("Printing XML:  ");
			System.out.println(xml);
			
			String xmlData = replaceXmlKeys(xml);
			System.out.println("Printing XMLData:  ");
			System.out.println(xmlData);
			
			JSONObject jsonObj = XML.toJSONObject(xmlData);
			//use a getter and setter to replace state
			//use a getter and setter to replace birthdate with age
			
			
			System.out.println("Printing JSON Object:  ");
			System.out.println(jsonObj.toString());
			//System.out.println(jsonObj.toString().split(",").length);
			
			FileWriter fileWriter = new FileWriter (patientXmlFile);
			
			BufferedWriter bufferedWriter = new BufferedWriter (fileWriter);
			
			for (int i=0; i<jsonObj.toString().split(",").length; i++) {
				System.out.println (jsonObj.toString().split(",")[i]);
				bufferedWriter.write(jsonObj.toString().split(",")[i]);
				bufferedWriter.write("\n");
			}
			
			bufferedWriter.close();
		} catch (IOException io) {
			System.out.println ("Error writing to file " + patientXmlFile);
			io.printStackTrace();
		}

	}

	private static String replaceXmlKeys(String xml) {
		String xmlData = xml;
		xmlData = xmlData.replace("id", "patientid");
		xmlData = xmlData.replace("gender", "sex");
		xmlData = xmlData.replace("dateOfBirth", "age");
		return xmlData;
	}

}
