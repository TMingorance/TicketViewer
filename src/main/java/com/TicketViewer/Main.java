package com.TicketViewer;

import com.TicketViewer.Model.ZendeskSingleton;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.Ticket;

import java.io.IOException;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        Zendesk zd = ZendeskSingleton.getInstance();

        String command = "curl https://{subdomain}.zendesk.com/api/v2/tickets/count.json -v -u {email_address}:{password}";
        Process process = Runtime.getRuntime().exec(command);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonMap = mapper.readValue(process.getInputStream(), Map.class);
        System.out.println(jsonMap);

        for(Ticket ticket : zd.getTickets()){
            System.out.println(ticket);
        }
    }
}
