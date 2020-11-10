package com.TicketViewer.Controller;

import com.TicketViewer.View.ErrorDisplay;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.zendesk.client.v2.model.Ticket;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class TicketListUI{//TODO methods that parse entries
    //TODO method that retrieves the Tickets and the count of tickets (so HTTP request + convert into JSON)

    private static int getCount() throws IOException {
        String command = "curl https://enssat.zendesk.com/api/v2/tickets/count.json -u tmingora@enssat.fr:123456";
        Process process = Runtime.getRuntime().exec(command);

        InputStream inputStream = process.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonMap = null;
        try {
            jsonMap = mapper.readValue(inputStream, Map.class);
        } catch (IOException e) {
            ErrorDisplay.errorDisplay(e.getMessage());
            ErrorController.exitOnInputError();
        }
        int count = -1;
        try {
            count = (int) ((Map<String, Object>) jsonMap.get("count")).get("value");
        }
        catch(Exception e){
            ErrorDisplay.errorDisplay(e.getMessage());
            ErrorController.exitOnInputError();
        }
        if(count >=0 ) {
            return count;
        }
        else{
            ErrorDisplay.errorDisplay("Ticket Count is negative.");
            ErrorController.exitOnInputError();
            return -1;
        }
    }


}
