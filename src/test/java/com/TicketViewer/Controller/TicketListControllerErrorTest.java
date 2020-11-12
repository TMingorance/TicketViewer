package com.TicketViewer.Controller;

import com.TicketViewer.Exceptions.ResourceNotFoundException;
import com.TicketViewer.Exceptions.UnavailableAPIException;
import com.TicketViewer.Model.TicketList;
import com.TicketViewer.View.ErrorDisplay;
import com.TicketViewer.View.TicketListDisplay;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TicketListControllerErrorTest extends TicketListController{

    @Mock
    private ErrorDisplay errorDisplay;

    @Mock
    private HttpConnectionHandler httpConnectionHandler;

    @Mock
    private MainPageController mainPageController;

    @Mock
    private TicketListDisplay ticketListDisplay;

    @Mock
    private ErrorManager errorManager;

    @Spy
    private TicketList ticketList;

    @InjectMocks
    private TicketListController ticketListController;

    public TicketListControllerErrorTest() {
        super();
    }

    @BeforeEach
    public void prepareTests() throws ResourceNotFoundException, IOException, UnavailableAPIException {
        Mockito.doNothing().when(errorManager).manageError((new IOException()).getMessage());
        Mockito.doNothing().when(errorManager).manageResourceNotFound();
        Mockito.doNothing().when(errorManager).manageUnavailableAPIException(Mockito.eq(403));
    }

    @Test
    public void prepareListErrorsTest () throws ResourceNotFoundException, IOException, UnavailableAPIException {
        Mockito.when(httpConnectionHandler.GETJSON(Mockito.anyString())).thenThrow(new IOException());
        ticketListController.prepareList();
        Mockito.verify(errorManager).manageError(Mockito.anyString());

        Mockito.when(httpConnectionHandler.GETJSON(Mockito.anyString())).thenThrow(new ResourceNotFoundException(404));
        ticketListController.prepareList();
        Mockito.verify(errorManager).manageResourceNotFound();

        Mockito.when(httpConnectionHandler.GETJSON(Mockito.anyString())).thenThrow(new UnavailableAPIException(403));
        ticketListController.prepareList();
        Mockito.verify(errorManager).manageUnavailableAPIException(Mockito.eq(403));
    }

    @Test
    public void updateCountErrorsTest () throws ResourceNotFoundException, IOException, UnavailableAPIException {
        Mockito.when(httpConnectionHandler.GETJSON(Mockito.anyString())).thenThrow(new IOException());
        ticketListController.updateCount();
        Mockito.verify(errorManager).manageError(Mockito.anyString());

        Mockito.when(httpConnectionHandler.GETJSON(Mockito.anyString())).thenThrow(new ResourceNotFoundException(404));
        ticketListController.updateCount();
        Mockito.verify(errorManager).manageResourceNotFound();

        Mockito.when(httpConnectionHandler.GETJSON(Mockito.anyString())).thenThrow(new UnavailableAPIException(403));
        ticketListController.updateCount();
        Mockito.verify(errorManager).manageUnavailableAPIException(Mockito.eq(403));
    }
}