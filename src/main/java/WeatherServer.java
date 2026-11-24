import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
public class WeatherServer {
    public static void main(String[] args)throws Exception{
        HttpServer server = HttpServer.create(new InetSocketAddress(8080),0);

       // server.createContext("/hello",exchange ->{
           // String text="Hello\n-API";

           // exchange.sendResponseHeaders(200, text.length());
          //  exchange.getResponseBody().write(text.getBytes());
           // exchange.close();

            server.createContext("/weather",e->{
                String text=WeatherDB.getLatestWeather();
               e.sendResponseHeaders(200,text.length());
               e.getResponseBody().write(text.getBytes());
                e.close();
            });
        server.start();
        System.out.println("server running on port 8080");

        }


    }

