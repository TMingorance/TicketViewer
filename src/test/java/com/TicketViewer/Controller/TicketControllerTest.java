package com.TicketViewer.Controller;

import com.TicketViewer.Exceptions.ResourceNotFoundException;
import com.TicketViewer.Exceptions.UnavailableAPIException;
import com.TicketViewer.Model.JsonTicket;
import com.TicketViewer.Model.TicketList;
import com.TicketViewer.View.ErrorDisplay;
import com.TicketViewer.View.MainPage;
import com.TicketViewer.View.TicketDisplay;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyMap;

@ExtendWith(MockitoExtension.class)
class TicketControllerTest {

    private static HttpConnectionHandler httpConnectionHandler = HttpConnectionHandler.getInstance();

    @Mock
    private JsonTicket jsonTicket;

    @Mock
    private MainPageController mainPageController;

    @Mock
    private ErrorDisplay errorDisplay;

    @Mock
    private TicketDisplay ticketDisplay;

    @Mock
    private ErrorManager errorManager;

    @Captor
    ArgumentCaptor<Map<String,Object>> captor;

    private TicketController ticketController;

    @BeforeAll
    public static void prepareTests(){
        httpConnectionHandler.initConnection();
    }

    @Test
    void whichTicketTestHappyPath() {
        ticketController = new TicketController(httpConnectionHandler,jsonTicket,ticketDisplay,errorManager);
        System.setIn(new ByteArrayInputStream("1\n\n".getBytes()));
        httpConnectionHandler.initConnection();

        ticketController.whichTicket();

        Mockito.verify(ticketDisplay).whichTicket();
        Mockito.verify(errorDisplay, Mockito.times(0)).wrongIdInput();

        Mockito.verify(jsonTicket).setTicket(captor.capture());
        Map<String,Object> ticket = captor.getValue();
        Assertions.assertNotNull(ticket);
        Assertions.assertNotNull(((Map<String,Object>)ticket.get("ticket")).get("id"));

        Mockito.verify(ticketDisplay).display();
    }
}