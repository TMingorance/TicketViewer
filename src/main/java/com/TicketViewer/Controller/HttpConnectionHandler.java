package com.TicketViewer.Controller;

import com.TicketViewer.Exceptions.ResourceNotFoundException;
import com.TicketViewer.View.ErrorDisplay;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Map;

public class HttpConnectionHandler {

    public static void initConnection(){
        final class BasicAuthenticator extends Authenticator {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("tmingora@enssat.fr", "123456".toCharArray());
            }
        }
        Authenticator.setDefault(new BasicAuthenticator());
    }

    public static Map<String, Object> GETJSON (String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            if (connection.getResponseCode() != 200) {
                if(connection.getResponseCode() == 404){
                    throw new ResourceNotFoundException();
                }
                ErrorDisplay.unavailableAPIDisplay(connection.getResponseCode());
                ErrorController.exitOrRestartOnInputError();
            }
            InputStream inputStream = connection.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> jsonMap = null;
            jsonMap = mapper.readValue(inputStream, Map.class);
            return jsonMap;
        }
        catch (Exception e){
            if(e.getClass() == ResourceNotFoundException.class){
                ErrorDisplay.resourceNotFound();
            }
            ErrorDisplay.errorDisplay(e.getMessage());
            ErrorController.exitOrRestartOnInputError();
        }
        return null;
    }
}
