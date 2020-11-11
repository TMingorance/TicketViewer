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

    private static void updateCount() throws IOException {//TODO check for HTTP codes : 200 is OK, the rest is errors

        Map<String,Object> jsonMap = HttpConnectionHandler.GETJSON("https://enssat.zendesk.com/api/v2/tickets/count.json");

        int count = -1;
        assert jsonMap != null;
        count = (int) ((Map<String,Object>)jsonMap.get("count")).get("value");
        if(count >=0 ) {
            TicketList.setTicketCount(count);
        }
        else{
            ErrorDisplay.errorDisplay("Ticket Count isn't right: its value is negative.");
            ErrorController.exitOnInputError();
        }
    }

    public static void updateNumberOfPages (){
        TicketList.setNumberOfPages(TicketList.getTicketCount()/25 +1);
    }

    public static void updateTicketList(boolean first, boolean next) throws IOException {//TODO gérer les pages
        Map<String, Object> jsonMap = null;
        updateCount();
        updateNumberOfPages();
        if(first) {
            jsonMap = HttpConnectionHandler.GETJSON("https://enssat.zendesk.com/api/v2/incremental/tickets.json?page[size]=25");
        }
        else if(next){//if it is not first, then is it next or prev ?
            jsonMap = HttpConnectionHandler.GETJSON(TicketList.getNextPageUrl());
        }
        else {//if it is not next, then it's prev
            jsonMap = HttpConnectionHandler.GETJSON(TicketList.getPrevPageUrl());
        }
        TicketList.setList(new ArrayList<Map<String, Object>>((Collection<Map<String, Object>>) jsonMap.get("tickets")));
        TicketList.setHasMore((boolean)((Map<String,Object>)jsonMap.get("meta")).get("has_more"));
        TicketListDisplay.update();
        TicketList.setPrevPageUrl((String)((Map<String,Object>)jsonMap.get("links")).get("prev"));
        if(TicketList.isHasMore() == true){
            TicketList.setPrevPageUrl((String)((Map<String,Object>)jsonMap.get("links")).get("next"));
        }
        else{
            TicketList.setNextPageUrl("");
        }
    }
}
