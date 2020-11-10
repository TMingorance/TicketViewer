package com.TicketViewer;

import com.TicketViewer.Model.ZendeskSingleton;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.chrono.StrictChronology;
import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.Ticket;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {


        String command = "curl https://enssat.zendesk.com/api/v2/incremental/tickets/cursor.json?start_time=0 -u tmingora@enssat.fr:123456";
        Process process = Runtime.getRuntime().exec(command);

        InputStream inputStream = process.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonMap = mapper.readValue(inputStream, Map.class);
        System.out.println(jsonMap.toString());

//        Zendesk zd = ZendeskSingleton.getInstance();
//        for(Ticket ticket : zd.getTickets()){
//            System.out.println(ticket);
//        }
    }
}
