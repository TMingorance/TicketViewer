package com.TicketViewer.Controller;

import com.TicketViewer.Exceptions.ResourceNotFoundException;
import com.TicketViewer.Exceptions.UnavailableAPIException;
import com.TicketViewer.View.ErrorDisplay;
import com.TicketViewer.View.MainPage;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

public class HttpConnectionHandler {

    private ErrorDisplay errorDisplay;
    private MainPage mainPage;

    private static volatile HttpConnectionHandler httpConnectionHandler = new HttpConnectionHandler();

    private HttpConnectionHandler(){
        this.errorDisplay = ErrorDisplay.getInstance();
        this.mainPage = MainPage.getInstance();
    }

    public static HttpConnectionHandler getInstance(){
        if(httpConnectionHandler != null) {
            return httpConnectionHandler;
        }
        else{
            httpConnectionHandler = new HttpConnectionHandler();
            return httpConnectionHandler;
        }
    }

    public void initConnection(){
        final class BasicAuthenticator extends Authenticator {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("tmingora@enssat.fr", "123456".toCharArray());
            }
        }
        Authenticator.setDefault(new BasicAuthenticator());
    }

    public Map<String, Object> GETJSON (String urlString) throws IOException, ResourceNotFoundException, UnavailableAPIException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        if (connection.getResponseCode() != 200) {
            if(connection.getResponseCode() == 404){
                throw new ResourceNotFoundException(connection.getResponseCode());
            }
            throw new UnavailableAPIException(connection.getResponseCode());
        }
        InputStream inputStream = connection.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonMap = null;
        jsonMap = mapper.readValue(inputStream, Map.class);
        return jsonMap;
    }
}
