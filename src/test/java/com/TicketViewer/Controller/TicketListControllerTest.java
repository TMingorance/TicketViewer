package com.TicketViewer.Controller;

import com.TicketViewer.Exceptions.ResourceNotFoundException;
import com.TicketViewer.Exceptions.UnavailableAPIException;
import com.TicketViewer.Model.TicketList;
import com.TicketViewer.View.ErrorDisplay;
import com.TicketViewer.View.TicketListDisplay;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class TicketListControllerTest {

    private static HttpConnectionHandler staticHttpConnectionHandler = HttpConnectionHandler.getInstance();

    private HttpConnectionHandler httpConnectionHandler = HttpConnectionHandler.getInstance();

    @Mock
    private ErrorDisplay errorDisplay;

    @Mock
    private TicketListDisplay ticketListDisplay;

    @Mock
    private TicketList ticketList;

    @InjectMocks
    private TicketListController ticketListController;

    @BeforeAll
    public static void prepareTests(){
        staticHttpConnectionHandler.initConnection();
    }

    @Test
    void prepareList() throws ResourceNotFoundException, IOException, UnavailableAPIException {
        Mockito.when(ticketList.isHasMore()).thenReturn(true);
        System.setIn(new ByteArrayInputStream("exit\n\n".getBytes()));
        ticketListController.prepareList();
        Mockito.verify(httpConnectionHandler,Mockito.times(2)).GETJSON(Mockito.anyString());
        Mockito.verify(ticketList).setTicketCount(Mockito.anyInt());
        Mockito.verify(ticketList).setNumberOfPages(Mockito.anyInt());
        Mockito.verify(ticketList).getTicketCount();
        Mockito.verify(ticketList).setCurrentPage(Mockito.anyInt());
        Mockito.verify(ticketList).setHasMore(Mockito.anyBoolean());
        Mockito.verify(ticketListDisplay).display();
        Mockito.verify(ticketList).setPrevPageUrl(Mockito.anyString());
        Mockito.verify(ticketList).isHasMore();
        Mockito.verify(ticketList).setNextPageUrl(Mockito.anyString());
    }
}