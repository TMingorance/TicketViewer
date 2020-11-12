package com.TicketViewer.Controller;

import com.TicketViewer.Exceptions.ResourceNotFoundException;
import com.TicketViewer.Exceptions.UnavailableAPIException;
import com.TicketViewer.Model.TicketList;
import com.TicketViewer.View.ErrorDisplay;
import com.TicketViewer.View.MainPage;
import com.TicketViewer.View.TicketListDisplay;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class TicketListControllerTest {

    private HttpConnectionHandler httpConnectionHandler = HttpConnectionHandler.getInstance();

    @Mock
    private MainPageController mainPageController;

    @Mock
    private TicketListDisplay ticketListDisplay;

    @Spy
    private TicketList ticketList;

    @Mock
    private ErrorManager errorManager;

    @InjectMocks
    private TicketListController ticketListController;

    @BeforeEach
    public void prepareTests(){
        httpConnectionHandler.initConnection();
        ticketListController = new TicketListController(httpConnectionHandler,ticketListDisplay,ticketList,errorManager);
    }

    @Test
    public void prepareListTestHappyPath() {
        Mockito.when(ticketList.isHasMore()).thenReturn(true);
        System.setIn(new ByteArrayInputStream("\n\n".getBytes()));

        ticketListController.prepareList();

        Assertions.assertNotNull(ticketList.getTicketCount());
        Assertions.assertNotNull(ticketList.getCurrentPage());
        Assertions.assertNotNull(ticketList.getNumberOfPages());
        Assertions.assertNotNull(ticketList.getPrevPageUrl());
        Assertions.assertNotNull(ticketList.getNextPageUrl());

        Assertions.assertTrue(ticketList.getTicketCount() != -1);
        Assertions.assertTrue(ticketList.getCurrentPage() != 0);
        Assertions.assertTrue(ticketList.getNumberOfPages() != -1);
        Assertions.assertTrue(ticketList.getNextPageUrl() != "");

        Mockito.verify(ticketList).setHasMore(Mockito.anyBoolean());
        Mockito.verify(ticketListDisplay).display();
        Mockito.verify(ticketList).setPrevPageUrl(Mockito.anyString());
        Mockito.verify(ticketList).isHasMore();

    }

}