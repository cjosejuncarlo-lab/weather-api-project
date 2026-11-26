import java.net.URI;
import java.net.http.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class WeatherAPI {
    public static void main(String[] args) throws  Exception {
     String URL ="https://api.open-meteo.com/v1/forecast?latitude=10.78&longitude=122.51&current_weather=true";
       HttpClient client =HttpClient.newHttpClient();//tinawag ang waiter or nag hire
        HttpRequest request=HttpRequest.newBuilder().uri(URI.create(URL)).build();//new bulder is the blank paper,uri is the create a address or write and build is to finished the address ready to sendd
       HttpResponse<String> response =client.send(request,HttpResponse.BodyHandlers.ofString());
       JsonNode data = new ObjectMapper().readTree(response.body());
 double temp =data.get("current_weather").get("temperature").asDouble();
double wind =data.get("current_weather").get("windspeed").asDouble();
       // System.out.println(temp);
       // System.out.println(response.statusCode());
        System.out.println(response.body());

        WeatherDB.createTable();
        WeatherDB.saveWeather("iloilo",temp,wind);
        WeatherDB.showWeather();

    }
}
