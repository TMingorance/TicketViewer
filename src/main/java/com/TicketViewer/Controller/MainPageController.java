package com.TicketViewer.Controller;

import java.util.Scanner;

public class MainPageController {

    private static TicketListController ticketListController;
    private static TicketController ticketController;

    private static volatile MainPageController mainPageController = new MainPageController();

    protected MainPageController() {//protected instead of private for testing
        ticketListController = TicketListController.getInstance();
        ticketController = TicketController.getInstance();
    }

    public static MainPageController getInstance(){
        if(mainPageController != null) {
            return mainPageController;
        }
        else{
            mainPageController = new MainPageController();
            return mainPageController;
        }
    }

    protected static void parseUserInput(String userInput) {
        String input = "";
        Scanner scanner = new Scanner(System.in);
        if(userInput.equals("")) {
            input = scanner.nextLine();
        }
        else{
            input = userInput;
        }
        if(input.equals("a")){
            ticketListController.prepareList();
        }
        else if(input.equals("d")){
            ticketController.whichTicket();
        }
        else if(input.equals("exit")){
            System.exit(0);
        }
        else{
            ErrorManager.wrongInputMainMenu();
        }
    }

}
