package com.TicketViewer.View;

import com.TicketViewer.Main;
import com.TicketViewer.Model.TicketList;
import org.zendesk.client.v2.model.Ticket;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class TicketListDisplay {
    //attributes displayed : id, type, subject, priority, status, created_at, updated_at

    public static void display(int currentPage) throws IOException { //the list should contain 25 tickets or less
        Runtime.getRuntime().exec("cls");
        System.out.println("*** Ticket List ***\n" +
                "   id   |  type  |       subject       |priority| status | created at | updated at |\n");
        for(Map<String, Object> ticket : TicketList.getList()){
            String id = (String) ticket.get("id");
            String type = (String) ticket.get("type");
            String subject = (String) ticket.get("subject");
            String priority = (String) ticket.get("priority");
            String status = (String) ticket.get("status");
            String created_at = (String) ticket.get("created_at");
            String updated_at = (String) ticket.get("updated_at");
            //adding spaces to all Strings for padding
            pad(id, 8);
            pad(type,8);
            pad(subject, 21);
            pad(priority,8);
            pad(status,8);
            pad(created_at, 12);
            pad(updated_at,12);
            //Cut the subject so it fits if it's too large
            if(subject.length() > 21){
                subject = subject.substring(0,17);
                subject = subject + "...";
            }
            System.out.println(id +"|"+ type + "|" + subject + "|" + priority + "|" + status + "|" + created_at + "|" + updated_at + "|\n");
        }
        System.out.println("Page " + currentPage + "/" + TicketList.getNumberOfPages() + "\n");
        if (currentPage <= 1){
            System.out.println("'n': next page\n");
        }
        else{
            System.out.println("'n': next page, 'p': previous page'\n");
        }
        MainPage.quickMenu();
    }

    public static void update() throws IOException {
        Runtime.getRuntime().exec("cls");
    }

    public static String pad(String string, int length){
        while(string.length()<length){
            string = string + " ";
        }
        return string;
    }
}
