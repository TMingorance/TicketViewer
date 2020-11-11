package com.TicketViewer;

import com.TicketViewer.Controller.HttpConnectionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        HttpConnectionHandler.initConnection();
        Map<String,Object> jsonMap = HttpConnectionHandler.GETJSON("https://enssat.zendesk.com/api/v2/tickets/count.json");

        int count = -1;
        assert jsonMap != null;
        count = (int) ((Map<String,Object>)jsonMap.get("count")).get("value");
        System.out.println(count);

        URL url = new URL("https://enssat.zendesk.com/api/v2/tickets.json?page[size]=25");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        HttpConnectionHandler.initConnection();

        connection.connect();

        System.out.println(connection.getResponseCode());
        System.out.println(connection.getResponseMessage());
        System.out.println(connection.getHeaderFields());
        InputStream inputStream = connection.getInputStream();

//        InputStream inputStream = process.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonMap2 = mapper.readValue(inputStream, Map.class);
        System.out.println(jsonMap2.toString());
    }
}
