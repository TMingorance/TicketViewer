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

public class TicketListUI{

    private HttpConnectionHandler httpConnectionHandler;
    private ErrorDisplay errorDisplay;
    private TicketListDisplay ticketListDisplay;
    private TicketList ticketList;


    private static volatile TicketListUI ticketListUI = new TicketListUI();

    private TicketListUI() {
        this.httpConnectionHandler = HttpConnectionHandler.getInstance();
        this.errorDisplay = ErrorDisplay.getInstance();
        this.ticketListDisplay = TicketListDisplay.getInstance();
        this.ticketList = TicketList.getInstance();
    }

    public static TicketListUI getInstance(){
        if(ticketListUI != null) {
            return ticketListUI;
        }
        else{
            ticketListUI = new TicketListUI();
            return ticketListUI;
        }
    }

    private void updateCount() {

        Map<String,Object> jsonMap = null;
        try {
            jsonMap = httpConnectionHandler.GETJSON("https://enssat.zendesk.com/api/v2/tickets/count.json");
        } catch (IOException e) {
            ErrorManager.manageError(e.getMessage());
        } catch (ResourceNotFoundException e) {
            ErrorManager.manageUnavailableAPIException(e.getErrorCode());
        } catch (UnavailableAPIException e) {
            ErrorManager.manageUnavailableAPIException(e.getErrorCode());
        }

        int count = -1;
        assert jsonMap != null;
        count = (int) ((Map<String,Object>)jsonMap.get("count")).get("value");
        if(count >=0 ) {
            ticketList.setTicketCount(count);
        }
        else{
            ErrorManager.manageError("Ticket Count isn't right: its value is negative.");
        }
    }

    private void updateNumberOfPages (){
        ticketList.setNumberOfPages(ticketList.getTicketCount()/25 +1);
    }

    public void prepareList(){
        updateTicketList(true, false);
        parseUserInput();
    }

    private void updateTicketList(boolean first, boolean next){//if first is true, next isn't considered
        Map<String, Object> jsonMap = null;
        updateCount();
        updateNumberOfPages();
        if(first) {
            try {
                jsonMap = httpConnectionHandler.GETJSON("https://enssat.zendesk.com/api/v2/tickets.json?page[size]=25");
            } catch (IOException e) {
                ErrorManager.manageError(e.getMessage());
            } catch (ResourceNotFoundException e) {
                ErrorManager.manageUnavailableAPIException(e.errorCode);
            } catch (UnavailableAPIException e) {
                ErrorManager.manageUnavailableAPIException(e.errorCode);
            }
            ticketList.setCurrentPage(1);
        }
        else if(next){//if it is not first, then is it next or prev ?
            try {
                jsonMap = httpConnectionHandler.GETJSON(ticketList.getNextPageUrl());
            } catch (IOException e) {
                ErrorManager.manageError(e.getMessage());
            } catch (ResourceNotFoundException e) {
                ErrorManager.manageUnavailableAPIException(e.getErrorCode());
            } catch (UnavailableAPIException e) {
                ErrorManager.manageUnavailableAPIException(e.getErrorCode());
            }
            ticketList.setCurrentPage(ticketList.getCurrentPage() + 1);
        }
        else {//if it is not next, then it's prev
            try {
                jsonMap = httpConnectionHandler.GETJSON(ticketList.getPrevPageUrl());
            } catch (IOException e) {
                ErrorManager.manageError(e.getMessage());
            } catch (ResourceNotFoundException e) {
                ErrorManager.manageUnavailableAPIException(e.getErrorCode());
            } catch (UnavailableAPIException e) {
                ErrorManager.manageUnavailableAPIException(e.getErrorCode());
            }
            ticketList.setCurrentPage(ticketList.getCurrentPage() -1);
        }
        ticketList.setList(new ArrayList<Map<String, Object>>((Collection<Map<String, Object>>) jsonMap.get("tickets")));
        ticketList.setHasMore((boolean)((Map<String,Object>)jsonMap.get("meta")).get("has_more"));
        ticketListDisplay.display();
        ticketList.setPrevPageUrl((String)((Map<String,Object>)jsonMap.get("links")).get("prev"));
        if(ticketList.isHasMore() == true){
            ticketList.setNextPageUrl((String)((Map<String,Object>)jsonMap.get("links")).get("next"));
        }
        else{
            ticketList.setNextPageUrl("");
        }
    }

    protected void parseUserInput(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if(input.equals("n")){
            if(ticketList.isHasMore()) {
                updateTicketList(false, true);
                parseUserInput();
            }
            else {
                wrongInputPageMenu();
            }
        }
        else if(input.equals("p")){
            if(ticketList.getCurrentPage() > 1) {
                updateTicketList(false, false);
                parseUserInput();
            }
            else{
                wrongInputPageMenu();
            }
        }
        else{
            MainPageUI.parseUserInput(input);
        }
    }
    private void wrongInputPageMenu(){
        errorDisplay.wrongInputPageMenu();
        ticketListUI.parseUserInput();
    }

}
