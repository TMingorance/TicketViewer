package Tests;

import com.TicketViewer.Controller.HttpConnectionHandler;
import com.TicketViewer.Exceptions.ResourceNotFoundException;
import com.TicketViewer.Exceptions.UnavailableAPIException;
import org.junit.gen5.api.Assertions;
import org.junit.gen5.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.util.Map;

public class HttpConnectionTest {//TODO tester si l'objet reçu est bien un int > 0 ou un Ticket JSON

    private HttpConnectionHandler httpConnectionHandler = HttpConnectionHandler.getInstance();

    @Test
    public void countRequestTest() throws ResourceNotFoundException, IOException, UnavailableAPIException {
        httpConnectionHandler.initConnection();
        Map<String,Object> jsonMap = httpConnectionHandler.GETJSON("https://enssat.zendesk.com/api/v2/tickets/count.json");

        int count = -1;
        Assertions.assertNotNull(jsonMap);
        count = (int) ((Map<String,Object>)jsonMap.get("count")).get("value");
        Assertions.assertTrue(count >= 0);
    }

    @Test
    public void ticketRequestTest() throws ResourceNotFoundException, IOException, UnavailableAPIException {
        httpConnectionHandler.initConnection();
        Map<String,Object> jsonMap = httpConnectionHandler.GETJSON("https://enssat.zendesk.com/api/v2/tickets.json?page[size]=25");

        Assertions.assertNotNull(jsonMap);
        Assertions.assertNotNull(jsonMap.get("tickets"));
        Assertions.assertNotNull(jsonMap.get("meta"));
        Assertions.assertNotNull(((Map<String,Object>)jsonMap.get("meta")).get("has_more"));
        Assertions.assertNotNull(jsonMap.get("links"));
    }

}
