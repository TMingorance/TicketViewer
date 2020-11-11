package com.TicketViewer.Controller;

import com.TicketViewer.Model.TicketList;
import com.TicketViewer.View.ErrorDisplay;
import com.TicketViewer.View.MainPage;
import com.TicketViewer.View.TicketDisplay;

import java.io.IOException;
import java.util.Scanner;

public class MainPageUI {

    public static void parseUserInput(String userInput) {
        String input = "";
        Scanner scanner = new Scanner(System.in);
        if(userInput.equals("")) {
            input = scanner.nextLine(); //TODO essayer next tout court
        }
        else{
            input = userInput;
        }
        if(input.equals("a")){
            TicketListUI.updateTicketList(true, false);
            TicketListUI.parseUserInput();
        }
        else if(input.equals("d")){
            TicketDisplay.whichTicket();
            input = scanner.nextLine();
            int id = 0;
            try {
                id = Integer.parseInt(input);
            }
            catch(NumberFormatException e){
                ErrorDisplay.wrongIdInput();
            }
            TicketUI.updateTicketDetails(id);
            MainPageUI.parseUserInput("");
        }
        else if(input.equals("exit")){
            System.exit(0);
        }
        else{
            System.out.println(input);
            ErrorDisplay.wrongInput();
        }
    }
}
