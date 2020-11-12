package com.TicketViewer.View;

import com.TicketViewer.Controller.Main;
import com.TicketViewer.Model.TicketList;

import java.util.Map;

public class TicketListDisplay {

    private TicketList ticketList;

    private static volatile TicketListDisplay ticketListDisplay = new TicketListDisplay();

    private TicketListDisplay(){
        this.ticketList = TicketList.getInstance();
    }

    public static TicketListDisplay getInstance(){
        if(ticketListDisplay != null) {
            return ticketListDisplay;
        }
        else{
            ticketListDisplay = new TicketListDisplay();
            return ticketListDisplay;
        }
    }
    //attributes displayed : id, type, subject, priority, status, created_at, updated_at

    public void display(){ //the list should contain 25 tickets or less
        Utilities.clearScreen();
        System.out.println("*** Ticket List ***\n" +
                "   id   |  type  |       subject       |priority| status |     created at    |     updated at    |\n");
        for(Map<String, Object> ticket : ticketList.getList()){
            String id = String.valueOf(ticket.get("id"));
            String type = String.valueOf(ticket.get("type"));
            String subject = String.valueOf(ticket.get("subject"));
            String priority = String.valueOf(ticket.get("priority"));
            String status = String.valueOf(ticket.get("status"));
            String created_at = String.valueOf(ticket.get("created_at"));
            String updated_at = String.valueOf(ticket.get("updated_at"));
            //adding spaces to all Strings for padding
            id = pad(id, 8);
            type = pad(type,8);
            subject = pad(subject, 21);
            priority = pad(priority,8);
            status = pad(status,8);
            created_at = pad(created_at, 19);
            updated_at = pad(updated_at,19);
            created_at = formatDate(created_at);
            updated_at = formatDate(updated_at);
            //Cut the subject so it fits if it's too large
            if(subject.length() > 21){
                subject = subject.substring(0,18);
                subject = subject + "...";
            }
            System.out.println(id +"|"+ type + "|" + subject + "|" + priority + "|" + status + "|" + created_at + "|" + updated_at + "|");
        }
        int currentPage = ticketList.getCurrentPage();
        System.out.println("Page " + currentPage + "/" + ticketList.getNumberOfPages() + "\n");
        pageCommandsIndications();
        MainPage.quickMenu();
    }

    private String pad(String string, int length){
        while(string.length()<length){
            string = string + " ";
        }
        return string;
    }

    private String formatDate(String date){
        StringBuilder stringBuilder = new StringBuilder(date);
        stringBuilder.setCharAt(10, ' ');
        stringBuilder.deleteCharAt(19);
        return stringBuilder.toString();
    }

    protected void pageCommandsIndications(){
        if (ticketList.isHasMore()){
            System.out.println("n: next page");
        }
        if (ticketList.getCurrentPage() > 1){
            System.out.println("p: previous page");
        }
    }
}
