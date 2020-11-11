package com.TicketViewer.Controller;

import com.TicketViewer.Model.TicketList;
import com.TicketViewer.View.TicketDisplay;

import java.io.IOException;
import java.util.Scanner;

public class MainPageUI {

    public static void parseUserInput() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); //TODO essayer next tout court
        if(input == "a"){
            TicketListUI.updateTicketList(true, false);
        }
        else if(input == "d"){
            TicketDisplay.whichTicket();
            input = scanner.nextLine();

        }
        else{

        }
    }
}
