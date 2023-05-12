package com.example.shorturlv3;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class CurrentTimeService {
    //Este servicio obtiene la fecha y hora actual a traves de la api de worldTime
    public String getCurrentTime(){
        try {
            URL url = new URL("http://worldtimeapi.org/api/timezone/America/Argentina/Buenos_Aires");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            Scanner in = new Scanner(connection.getInputStream());
            String response = in.nextLine();
            in.close();

            String datetime = response.substring(response.indexOf("datetime") + 11, response.indexOf("datetime") + 30);
            //System.out.println("Current datetime: " + datetime);
            return datetime;

        }
        catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    }






