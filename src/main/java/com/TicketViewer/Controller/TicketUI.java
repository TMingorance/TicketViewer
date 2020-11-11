package com.TicketViewer.Controller;

import com.TicketViewer.Model.JsonTicket;
import com.TicketViewer.View.TicketDisplay;

import java.io.IOException;
import java.util.Map;

public class TicketUI {
    public static void updateTicketDetails (int id) throws IOException {
        Map<String,Object> jsonMap = HttpConnectionHandler.GETJSON("https://enssat.zendesk.com/api/v2/tickets/"+ id +".json");
        JsonTicket.setTicket(jsonMap);
        TicketDisplay.display();
    }
}
