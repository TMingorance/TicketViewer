package com.TicketViewer.Model;

import java.util.Map;

public class JsonTicket {

    private static volatile JsonTicket jsonTicket = new JsonTicket();

    private JsonTicket(){}

    public static JsonTicket getInstance(){
        if(jsonTicket != null) {
            return jsonTicket;
        }
        else{
            jsonTicket = new JsonTicket();
            return jsonTicket;
        }
    }

    public Map<String, Object> ticket = null;

    public Map<String, Object> getTicket() {
        return ticket;
    }

    public void setTicket(Map<String, Object> ticket) {
        this.ticket = ticket;
    }
}
