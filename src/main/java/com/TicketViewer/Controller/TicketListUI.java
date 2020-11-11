package com.TicketViewer.Controller;

import com.TicketViewer.Model.TicketList;
import com.TicketViewer.View.ErrorDisplay;
import com.TicketViewer.View.TicketListDisplay;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Scanner;

public class TicketListUI{//TODO methods that parse entries
    //TODO method that retrieves the Tickets and the count of tickets (so HTTP request + convert into JSON)

    private static void updateCount() {//TODO check for HTTP codes : 200 is OK, the rest is errors

        Map<String,Object> jsonMap = HttpConnectionHandler.GETJSON("https://enssat.zendesk.com/api/v2/tickets/count.json");

        int count = -1;
        assert jsonMap != null;
        count = (int) ((Map<String,Object>)jsonMap.get("count")).get("value");
        if(count >=0 ) {
            TicketList.setTicketCount(count);
        }
        else{
            ErrorDisplay.errorDisplay("Ticket Count isn't right: its value is negative.");
            ErrorController.exitOrRestartOnInputError();
        }
    }

    public static void updateNumberOfPages (){
        TicketList.setNumberOfPages(TicketList.getTicketCount()/25 +1);
    }

    public static void updateTicketList(boolean first, boolean next){//if first is true, next isn't considered
        Map<String, Object> jsonMap = null;
        updateCount();
        updateNumberOfPages();
        if(first) {
            jsonMap = HttpConnectionHandler.GETJSON("https://enssat.zendesk.com/api/v2/tickets.json?page[size]=25");
            TicketList.setCurrentPage(1);
        }
        else if(next){//if it is not first, then is it next or prev ?
            jsonMap = HttpConnectionHandler.GETJSON(TicketList.getNextPageUrl());
            TicketList.setCurrentPage(TicketList.getCurrentPage() + 1);
        }
        else {//if it is not next, then it's prev
            jsonMap = HttpConnectionHandler.GETJSON(TicketList.getPrevPageUrl());
            TicketList.setCurrentPage(TicketList.getCurrentPage() -1);
        }
        TicketList.setList(new ArrayList<Map<String, Object>>((Collection<Map<String, Object>>) jsonMap.get("tickets")));
        TicketList.setHasMore((boolean)((Map<String,Object>)jsonMap.get("meta")).get("has_more"));
        TicketListDisplay.display();
        TicketList.setPrevPageUrl((String)((Map<String,Object>)jsonMap.get("links")).get("prev"));
        if(TicketList.isHasMore() == true){
            TicketList.setNextPageUrl((String)((Map<String,Object>)jsonMap.get("links")).get("next"));
        }
        else{
            TicketList.setNextPageUrl("");
        }
    }

    public static void parseUserInput(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if(input.equals("n")){
            if(TicketList.isHasMore()) {
                updateTicketList(false, true);
                parseUserInput();
            }
            else {
                ErrorDisplay.wrongInput();
                TicketListDisplay.pageCommandsIndications(TicketList.getCurrentPage());
                parseUserInput();
            }
        }
        else if(input.equals("p")){
            if(TicketList.getCurrentPage() > 1) {
                updateTicketList(false, false);
                parseUserInput();
            }
            else{
                ErrorDisplay.wrongInput();
                TicketListDisplay.pageCommandsIndications(TicketList.getCurrentPage());
                parseUserInput();
            }
        }
        else{
            MainPageUI.parseUserInput(input);
        }
    }
}
