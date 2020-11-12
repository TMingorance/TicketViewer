package com.TicketViewer.Controller;

import com.TicketViewer.Model.TicketList;
import com.TicketViewer.View.MainPage;

import java.util.Scanner;

public class MainPageController implements Controller{

    private TicketListController ticketListController;
    private TicketController ticketController;
    private MainPage mainPage;

    private static volatile MainPageController mainPageController = new MainPageController();

    protected MainPageController() {//protected instead of private for testing
        this.ticketListController = TicketListController.getInstance();
        this.ticketController = TicketController.getInstance();
        this.mainPage = MainPage.getInstance();
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

    public void control(String command) {
        if(command == ""){
            mainPage.quickMenu();
            parseUserInput("");
        }
        else {
            parseUserInput(command);
        }
    }

    protected void parseUserInput(String userInput) {
        String input = "";
        Scanner scanner = new Scanner(System.in);
        if(userInput.equals("")) {
            input = scanner.nextLine();
        }
        else{
            input = userInput;
        }
        if(input.equals("a")){
            MainController.setController(TicketListController.getInstance());
            MainController.run("");
        }
        else if(input.equals("d")){
            MainController.setController(TicketController.getInstance());
            MainController.run("");
        }
        else if(input.equals("exit")){
            System.exit(0);
        }
        else{
            ErrorManager.wrongInputMainMenu();
        }
    }


}
