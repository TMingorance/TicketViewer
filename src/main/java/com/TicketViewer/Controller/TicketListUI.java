package com.TicketViewer.Controller;

import com.TicketViewer.Model.TicketList;
import com.TicketViewer.View.ErrorDisplay;
import com.TicketViewer.View.TicketListDisplay;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class TicketListUI{//TODO methods that parse entries
    //TODO method that retrieves the Tickets and the count of tickets (so HTTP request + convert into JSON)


    private static void UpdateCount() throws IOException {//TODO check for HTTP codes : 200 is OK, the rest is errors

        Map<String,Object> jsonMap = HttpConnectionHandler.GETJSON("https://enssat.zendesk.com/api/v2/tickets/count.json");

        int count = -1;
        assert jsonMap != null;
        count = (int) ((Map<String,Object>)jsonMap.get("count")).get("value");
        if(count >=0 ) {
            TicketList.setTicketCount(count);
        }
        else{
            ErrorDisplay.errorDisplay("Ticket Count is negative.");
            ErrorController.exitOnInputError();
        }
    }

    public static void updateNumberOfPages (){
        TicketList.setNumberOfPages(TicketList.getTicketCount()/25 +1);
    }

    public static void updateTicketList() throws IOException {
        Map<String, Object> jsonMap = HttpConnectionHandler.GETJSON("https://enssat.zendesk.com/api/v2/incremental/tickets.json?page[size]=25");
        TicketList.setList(new ArrayList<Map<String,Object>>((Collection<Map<String, Object>>) jsonMap.get("tickets")));
        TicketListDisplay.update();
        //if(jsonMap.get("meta[has_more]"))
    }
}
