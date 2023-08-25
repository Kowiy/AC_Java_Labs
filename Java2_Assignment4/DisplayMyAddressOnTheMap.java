package Jsonproj.Labassignment;


import org.json.JSONObject; 
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
/*
 * Class for converting address data from a CSV file into 
 * latitudinal and longitudinal coordinates using the API, 
 * and then writing the coordinates to a CSV file.
 */
public class DisplayMyAddressOnTheMap {
	// Google Maps API key
    private static final String API_KEY = "My_API_Key";
    //Fetches the coordinates of the address
    private static JSONObject fetchLatLong(Address address) throws IOException {
        String baseUrl = "https://maps.googleapis.com/maps/api/geocode/json";
        String query = URLEncoder.encode(address.getUrlEncodedAddress(), "UTF-8");

        URL url = new URL(baseUrl + "?address=" + query + "&key=" + API_KEY);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        connection.disconnect();

        return new JSONObject(content.toString());
    }
    //parse a cvs file to extract address
    private static List<Address> parseCSV(String filePath) throws IOException {
        List<Address> addresses = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                addresses.add(Address.fromCSV(line)); 
                }
        }
        return addresses;
    }
    //Writes address and coordinates into a CSV file.
    private static void writeLatLongCSV(List<Address> addresses, String outputFile) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            bw.write("Latitude,Longitude,Name,Icon,IconScale,IconAltitude\n");
            for (Address address : addresses) {
            	if (address.getUrlEncodedAddress().isEmpty()) {
                    System.err.println("Skipping empty address.");
                    continue; 
                }
            	
            	JSONObject result = fetchLatLong(address);
                JSONObject location = result.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
                String name = address.getFirstName() + " " + address.getLastName();
                if (address.getSpouseFirstName() != null && !address.getSpouseFirstName().isEmpty()) {
                    name += " and " + address.getSpouseFirstName() + " " + address.getSpouseLastName();
                }
                bw.write(location.getDouble("lat") + "," + location.getDouble("lng") + "," + name + ",111,1,1\n");
            }
        }
    }

    public static void main(String[] args) {
        String inputFilePath = "C:\\Users\\User\\Desktop\\Term2_Computer_Programming\\Java2(OOP)\\Kowiy_Assignment3\\OutputAddress.csv";
        String outputFilePath = "C:\\Users\\User\\Desktop\\Term2_Computer_Programming\\Java2(OOP)\\Kowiy_Assignment3\\LatLong.csv";

        try {
            List<Address> addresses = parseCSV(inputFilePath);
            writeLatLongCSV(addresses, outputFilePath);
            System.out.println("LatLong.csv has been created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
