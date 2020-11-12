package com.TicketViewer.Controller;

import com.TicketViewer.Exceptions.ResourceNotFoundException;
import com.TicketViewer.Exceptions.UnavailableAPIException;
import com.TicketViewer.Model.JsonTicket;
import com.TicketViewer.View.ErrorDisplay;
import com.TicketViewer.View.TicketDisplay;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class TicketController implements Controller{

    private HttpConnectionHandler httpConnectionHandler;
    private JsonTicket jsonTicket;
    private TicketDisplay ticketDisplay;

    private static volatile TicketController ticketController = new TicketController();

    private TicketController(){
        this.httpConnectionHandler = HttpConnectionHandler.getInstance();
        this.jsonTicket = JsonTicket.getInstance();
        this.ticketDisplay = TicketDisplay.getInstance();
    }

    public TicketController(int forTest){} //Public constructor for running tests

    public static TicketController getInstance(){
        if(ticketController != null) {
            return ticketController;
        }
        else{
            ticketController = new TicketController();
            return ticketController;
        }
    }

    public void control(String command) {
        whichTicket();
        MainController.setController(MainPageController.getInstance());
        MainController.run("");
    }

    public void whichTicket(){
        ticketDisplay.whichTicket();
        parseIdInput();
    }

    private void parseIdInput(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int id = 0;
        try {
            id = Integer.parseInt(input);
        }
        catch(NumberFormatException e){
            ErrorManager.wrongIdInput();
        }
        ticketController.updateTicketDetails(id);
    }

    private void updateTicketDetails (int id){
        Map<String, Object> jsonMap = null;
        try {
            jsonMap = httpConnectionHandler.GETJSON("https://enssat.zendesk.com/api/v2/tickets/" + id + ".json");
        } catch (IOException e) {
            ErrorManager.manageError(e.getMessage());
        } catch (ResourceNotFoundException e) {
            ErrorManager.manageResourceNotFound();
        } catch (UnavailableAPIException e) {
            ErrorManager.manageUnavailableAPIException(e.getErrorCode());
        }
        jsonTicket.setTicket(jsonMap);
        ticketDisplay.display();
    }





}
