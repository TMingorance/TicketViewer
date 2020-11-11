package com.TicketViewer.Controller;

import com.TicketViewer.View.ErrorDisplay;
import com.TicketViewer.View.MainPage;

import java.util.Scanner;

public class MainPageUI {

    private static TicketListUI ticketListUI;
    private static TicketUI ticketUI;
    private static MainPage mainPage;

    private static volatile MainPageUI mainPageUI = new MainPageUI();

    private MainPageUI() {
        this.ticketListUI = TicketListUI.getInstance();
        this.ticketUI = TicketUI.getInstance();
        this.mainPage = MainPage.getInstance();
    }

    public static MainPageUI getInstance(){
        if(mainPageUI != null) {
            return mainPageUI;
        }
        else{
            mainPageUI = new MainPageUI();
            return mainPageUI;
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
            ticketListUI.prepareList();
        }
        else if(input.equals("d")){
            ticketUI.whichTicket();
        }
        else if(input.equals("exit")){
            System.exit(0);
        }
        else{
            ErrorManager.wrongInputMainMenu();
        }
    }

    protected static void exitOrRestartOnInputError() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("menu")){
            mainPage.welcome();
            MainPageUI.parseUserInput("");
        }
        else {
            System.exit(-1);
        }
    }

}
