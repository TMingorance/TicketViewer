package com.TicketViewer.Controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class MainPageControllerTest {

    @Mock
    private TicketListController ticketListController;

    @Mock
    private TicketController ticketController;

    @InjectMocks
    private MainPageController mainPageController;

    @Test
    public void parseUserInputTest(){
        try {
            System.setIn(new ByteArrayInputStream("a\n".getBytes()));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        MainPageController.parseUserInput("");
        Mockito.verify(ticketListController).prepareList();

        System.setIn(new ByteArrayInputStream("d\n".getBytes()));
        MainPageController.parseUserInput("");
        Mockito.verify(ticketController).whichTicket();

        //Test that System.exit(0) is called (tricky to do)

        System.setIn(new ByteArrayInputStream("z\n".getBytes()));
        PowerMockito.mockStatic(ErrorManager.class);
        MainPageController.parseUserInput("");
        PowerMockito.verifyStatic();
    }
}