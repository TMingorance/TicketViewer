package com.TicketViewer.Controller;

import com.TicketViewer.Exceptions.ResourceNotFoundException;
import com.TicketViewer.Exceptions.UnavailableAPIException;
import com.TicketViewer.Model.JsonTicket;
import com.TicketViewer.Model.TicketList;
import com.TicketViewer.View.ErrorDisplay;
import com.TicketViewer.View.TicketDisplay;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TicketControllerTest {

    private static HttpConnectionHandler httpConnectionHandler = HttpConnectionHandler.getInstance();
    @Mock
    private JsonTicket jsonTicket;
    @Mock
    private TicketDisplay ticketDisplay;
    @Mock
    private ErrorDisplay errorDisplay;

    @InjectMocks
    private TicketController ticketController = new TicketController(0);

    @BeforeAll
    public static void prepareTests(){
        httpConnectionHandler.initConnection();
    }

    @Test
    void whichTicketTestHappyPath() throws ResourceNotFoundException, IOException, UnavailableAPIException {
        System.setIn(new ByteArrayInputStream("1\n\n".getBytes()));
        httpConnectionHandler.initConnection();
        ticketController.whichTicket();
        Mockito.verify(ticketDisplay).whichTicket();
        Mockito.verify(errorDisplay, Mockito.times(0)).wrongIdInput();
        Mockito.verify(httpConnectionHandler).GETJSON(Mockito.anyString());
        Mockito.verify(jsonTicket).setTicket(Mockito.<String, Object>anyMap());
        Mockito.verify(ticketDisplay).display();
    }
}