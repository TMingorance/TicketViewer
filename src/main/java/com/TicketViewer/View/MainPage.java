package com.TicketViewer.View;

public class MainPage {

    private static volatile MainPage mainPage = new MainPage();

    private MainPage(){}

    public static MainPage getInstance(){
        if(mainPage != null) {
            return mainPage;
        }
        else{
            mainPage = new MainPage();
            return mainPage;
        }
    }
    public void welcome(){
        Utilities.clearScreen();
        System.out.println("Welcome to Ticket Viewer !\n" +
                "           What would you like to do ?\n" +
                "\n" +
                "           -> Enter 'a' to view all tickets\n" +
                "           -> Enter 'd' to view one ticket in details\n" +
                "           -> Enter 'exit' quit close this program\n\n");
    }

    public static void quickMenu(){
        System.out.println("a: View all tickets; d: View ticket details; quit: exit");
    }
}
