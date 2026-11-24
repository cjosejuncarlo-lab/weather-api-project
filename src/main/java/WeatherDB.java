import javax.swing.*;
import java.sql.*;
public class WeatherDB {

    public static Connection conn(){
        final String URL="jdbc:sqlite:weather.db";
        try{
            return DriverManager.getConnection(URL);
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return null;


    }

    public static void createTable(){
        Connection conn=conn();
            String sql="CREATE TABLE IF NOT EXISTS weather (id INTEGER PRIMARY KEY,city TEXT, temperature REAL, windspeed REAL)";
            try{
                Statement st=conn.createStatement();
                st.execute(sql);
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null,e.getMessage());
            }

    }


    public static void saveWeather(String city,double temp,double wind){
        String sql="INSERT INTO WEATHER (city,temperature,windspeed) VALUES (?,?,?)";
        try{
            Connection conn=conn();
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,city);
            ps.setDouble(2,temp);
            ps.setDouble(3,wind);
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

    }

    public static void showWeather(){
        try{
            Statement st = conn().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM weather");
            while(rs.next()){
                System.out.println(rs.getInt(1)+" | "+rs.getString("city")+" | "+rs.getDouble("temperature")+" | "+rs.getDouble("windspeed"));
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static String getLatestWeather(){
        try{
            Connection conn=conn();
           Statement st = conn.createStatement();
           ResultSet rs=st.executeQuery("SELECT * FROM weather ORDER BY id DESC LIMIT 1");
           while (rs.next()){
               return "{\"city\":\""+rs.getString("city")+"\",\"temperature\":"+rs.getDouble("temperature")+
                       ",\"windspeed\":"+rs.getDouble("windspeed")+"}";
           }


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return "{}";
    }
}
