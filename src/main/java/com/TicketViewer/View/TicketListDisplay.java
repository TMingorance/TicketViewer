package com.TicketViewer.View;

import com.TicketViewer.Model.TicketList;
import org.zendesk.client.v2.model.Ticket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class TicketListDisplay {
    //attributes displayed : id, type, subject, priority, status, created_at, updated_at

    public static void display(ArrayList<Ticket> list, int currentPage, int totalPages) throws IOException { //the list should contain 25 tickets or less
        Runtime.getRuntime().exec("cls");
        System.out.println("*** Ticket List ***\n" +
                "   id   |  type  |       subject       |priority| status | created at | updated at |");
        for(Map<String, Object> ticket : TicketList.getList()){
            String subject = (String) ticket.get("subject");
            //Cut the subject so it fits
            if(subject.length() > 21){
                subject = subject.substring(0,17);
                subject = subject + "...";
            }
            //System
        }
    }

    public static void update() throws IOException {
        Runtime.getRuntime().exec("cls");
    }
}
