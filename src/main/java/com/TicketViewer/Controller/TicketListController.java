package com.TicketViewer.Controller;

import com.TicketViewer.Exceptions.ResourceNotFoundException;
import com.TicketViewer.Exceptions.UnavailableAPIException;
import com.TicketViewer.Model.TicketList;
import com.TicketViewer.View.ErrorDisplay;
import com.TicketViewer.View.MainPage;
import com.TicketViewer.View.TicketListDisplay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Scanner;

public class TicketListController implements Controller{

    private HttpConnectionHandler httpConnectionHandler;
    private TicketListDisplay ticketListDisplay;
    private TicketList ticketList;
    private ErrorManager errorManager;


    private static volatile TicketListController ticketListController = new TicketListController();

    public TicketListController() {//public for testing
        this.httpConnectionHandler = HttpConnectionHandler.getInstance();
        this.ticketListDisplay = TicketListDisplay.getInstance();
        this.ticketList = TicketList.getInstance();
        this.errorManager = ErrorManager.getInstance();
    }

    public TicketListController(HttpConnectionHandler httpConnectionHandler, TicketListDisplay ticketListDisplay, TicketList ticketList, ErrorManager errorManager){
        this.httpConnectionHandler = httpConnectionHandler;
        this.ticketListDisplay = ticketListDisplay;
        this.ticketList = ticketList;
        this.errorManager = errorManager;
    }

    public static TicketListController getInstance(){
        if(ticketListController != null) {
            return ticketListController;
        }
        else{
            ticketListController = new TicketListController();
            return ticketListController;
        }
    }

    public void control(String command) {
        if(command == "") {
            String input = prepareList();
            MainController.setController(MainPageController.getInstance());
            MainController.run(input);
        }
        if(command == "page error recover"){
            String input = parseUserInput();
            MainController.setController(MainPageController.getInstance());
            MainController.run(input);
        }
    }

    protected void updateCount() {

        Map<String,Object> jsonMap = null;
        try {
            jsonMap = httpConnectionHandler.GETJSON("https://enssat.zendesk.com/api/v2/tickets/count.json");
        } catch (IOException e) {
            errorManager.manageError(e.getMessage());
        } catch (ResourceNotFoundException e) {
            errorManager.manageUnavailableAPIException(e.getErrorCode());
        } catch (UnavailableAPIException e) {
            errorManager.manageUnavailableAPIException(e.getErrorCode());
        }

        int count = -1;
        count = ((Integer) ((Map<String,Object>)jsonMap.get("count")).get("value")).intValue();
        if(count >=0 ) {
            ticketList.setTicketCount(count);
        }
        else{
            errorManager.manageError("Ticket Count isn't right: its value is negative.");
        }
    }

    private void updateNumberOfPages (){
        ticketList.setNumberOfPages(ticketList.getTicketCount()/25 +1);
    }

    public String prepareList(){//public for testing
        updateTicketList(true, false);
        String input = parseUserInput();
        return input;
    }

    protected void updateTicketList(boolean first, boolean next){//if first is true, next isn't considered
        Map<String, Object> jsonMap = null;
        updateCount();
        updateNumberOfPages();
        if(first) {
            try {
                jsonMap = httpConnectionHandler.GETJSON("https://enssat.zendesk.com/api/v2/tickets.json?page[size]=25");
            } catch (IOException e) {
                errorManager.manageError(e.getMessage());
            } catch (ResourceNotFoundException e) {
                errorManager.manageUnavailableAPIException(e.errorCode);
            } catch (UnavailableAPIException e) {
                errorManager.manageUnavailableAPIException(e.errorCode);
            }
            ticketList.setCurrentPage(1);
        }
        else if(next){//if it is not first, then is it next or prev ?
            try {
                jsonMap = httpConnectionHandler.GETJSON(ticketList.getNextPageUrl());
            } catch (IOException e) {
                errorManager.manageError(e.getMessage());
            } catch (ResourceNotFoundException e) {
                errorManager.manageUnavailableAPIException(e.getErrorCode());
            } catch (UnavailableAPIException e) {
                errorManager.manageUnavailableAPIException(e.getErrorCode());
            }
            ticketList.setCurrentPage(ticketList.getCurrentPage() + 1);
        }
        else {//if it is not next, then it's prev
            try {
                jsonMap = httpConnectionHandler.GETJSON(ticketList.getPrevPageUrl());
            } catch (IOException e) {
                errorManager.manageError(e.getMessage());
            } catch (ResourceNotFoundException e) {
                errorManager.manageUnavailableAPIException(e.getErrorCode());
            } catch (UnavailableAPIException e) {
                errorManager.manageUnavailableAPIException(e.getErrorCode());
            }
            ticketList.setCurrentPage(ticketList.getCurrentPage() -1);
        }
        ticketList.setList(new ArrayList<Map<String, Object>>((Collection<Map<String, Object>>) jsonMap.get("tickets")));
        ticketList.setHasMore(((Boolean) ((Map<String,Object>)jsonMap.get("meta")).get("has_more")).booleanValue());
        ticketListDisplay.display();
        ticketList.setPrevPageUrl((String)((Map<String,Object>)jsonMap.get("links")).get("prev"));
        if(ticketList.isHasMore()){
            ticketList.setNextPageUrl((String)((Map<String,Object>)jsonMap.get("links")).get("next"));
        }
        else{
            ticketList.setNextPageUrl("");
        }
    }

    private String parseUserInput(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if(input.equals("n")){
            if(ticketList.isHasMore()) {
                updateTicketList(false, true);
                parseUserInput();
            }
            else {
                errorManager.wrongInputPageMenu();
            }
        }
        else if(input.equals("p")){
            if(ticketList.getCurrentPage() > 1) {
                updateTicketList(false, false);
                parseUserInput();
            }
            else{
                errorManager.wrongInputPageMenu();
            }
        }
            return input;
    }

}
