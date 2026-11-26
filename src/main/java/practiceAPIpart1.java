import java.net.URI;
import java.net.http.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
public class practiceAPIpart1 {


    public static void main(String[] args) throws Exception {
       String URL="https://api.open-meteo.com/v1/forecast?latitude=10.78&longitude=122.51&current_weather=true";
         HttpClient waiter = HttpClient.newHttpClient();
         HttpRequest order = HttpRequest.newBuilder().uri(URI.create(URL)).build();
         HttpResponse<String> sender =waiter.send(order,HttpResponse.BodyHandlers.ofString());
         JsonNode data=new ObjectMapper().readTree(sender.body());

        int sec=data.get("current_weather").get("interval").asInt();
        double temp =data.get("current_weather").get("temperature").asDouble();
        double wind =data.get("current_weather").get("windspeed").asDouble();


         WeatherDB.createTable();
         WeatherDB.showWeather();
        WeatherDB.saveWeather("Iloilo", temp, wind);


    }
}