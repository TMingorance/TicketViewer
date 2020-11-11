package com.TicketViewer.View;

import com.TicketViewer.Controller.ErrorController;
import com.TicketViewer.Main;
import com.TicketViewer.Model.TicketList;
import org.zendesk.client.v2.model.Ticket;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class TicketListDisplay {
    //attributes displayed : id, type, subject, priority, status, created_at, updated_at

    public static void display(){ //the list should contain 25 tickets or less
        MainPage.clearScreen();
        System.out.println("*** Ticket List ***\n" +
                "   id   |  type  |       subject       |priority| status |     created at    |     updated at    |\n");
        for(Map<String, Object> ticket : TicketList.getList()){
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
        int currentPage = TicketList.getCurrentPage();
        System.out.println("Page " + currentPage + "/" + TicketList.getNumberOfPages() + "\n");
        pageCommandsIndications(currentPage);
        MainPage.quickMenu();
    }

    public static String pad(String string, int length){
        while(string.length()<length){
            string = string + " ";
        }
        return string;
    }

    public static String formatDate(String date){
        StringBuilder stringBuilder = new StringBuilder(date);
        stringBuilder.setCharAt(10, ' ');
        stringBuilder.deleteCharAt(19);
        return stringBuilder.toString();
    }

    public static void pageCommandsIndications(int currentPage){
        if (TicketList.isHasMore()){
            System.out.println("'n': next page"); //TODO mettre un passage vers la dernière page à partir de la première ?
        }
        if (currentPage > 1){
            System.out.println("'p': previous page'");
        }
        System.out.println("\n");
    }
}
