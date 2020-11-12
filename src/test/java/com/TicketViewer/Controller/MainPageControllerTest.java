package com.TicketViewer.Controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MainPageControllerTest {

    private static HttpConnectionHandler httpConnectionHandler = HttpConnectionHandler.getInstance();

    @Mock
    private TicketListController ticketListController;

    @Mock
    private TicketController ticketController;

    @InjectMocks
    private MainPageController mainPageController = MainPageController.getInstance();

    @BeforeAll
    public static void prepareTests(){
        httpConnectionHandler.initConnection();
    }

    @Test
    public void parseUserInputTestHappyPath(){

        System.setIn(new ByteArrayInputStream("a\n".getBytes()));

        MainPageController.parseUserInput("");
        Mockito.verify(ticketListController).prepareList();

        System.setIn(new ByteArrayInputStream("d\n".getBytes()));
        MainPageController.parseUserInput("");
        Mockito.verify(ticketController).whichTicket();

        //Test that System.exit(0) is called (tricky to do)

        //Test that static method is called (tricky to do, needs PowerMock)
    }
}