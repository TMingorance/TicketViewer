package com.TicketViewer.Model;

import java.util.Map;

public class JsonTicket {
    public static Map<String, Object> ticket = null;

    public static Map<String, Object> getTicket() {
        return ticket;
    }

    public static void setTicket(Map<String, Object> ticket) {
        JsonTicket.ticket = ticket;
    }
}
