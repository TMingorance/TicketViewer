package com.TicketViewer.Controller;

import com.TicketViewer.Exceptions.ResourceNotFoundException;
import com.TicketViewer.Exceptions.UnavailableAPIException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HttpConnectionHandlerTest {

    private HttpConnectionHandler httpConnectionHandler = HttpConnectionHandler.getInstance();

    @Test
    public void countRequestTestHappyPath() throws ResourceNotFoundException, IOException, UnavailableAPIException {
        httpConnectionHandler.initConnection();
        Map<String,Object> jsonMap = httpConnectionHandler.GETJSON("https://enssat.zendesk.com/api/v2/tickets/count.json");

        int count = -1;
        Assertions.assertNotNull(jsonMap);
        count = (Integer) ((Map<String,Object>)jsonMap.get("count")).get("value");
        Assertions.assertTrue(count >= 0);
    }

    @Test
    public void ticketRequestTestHappyPath() throws ResourceNotFoundException, IOException, UnavailableAPIException {
        httpConnectionHandler.initConnection();
        Map<String,Object> jsonMap = httpConnectionHandler.GETJSON("https://enssat.zendesk.com/api/v2/tickets.json?page[size]=25");

        Assertions.assertNotNull(jsonMap);
        Assertions.assertNotNull(jsonMap.get("tickets"));
        Assertions.assertNotNull(jsonMap.get("meta"));
        Assertions.assertNotNull(((Map<String,Object>)jsonMap.get("meta")).get("has_more"));
        Assertions.assertNotNull(jsonMap.get("links"));
    }
}