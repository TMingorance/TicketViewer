package com.TicketViewer.View;

import com.TicketViewer.Controller.MainPageUI;
import com.TicketViewer.Main;

import java.io.IOException;

public class ErrorDisplay {
    public static void unavailableAPIDisplay(int errorCode){
        System.out.println("Error : Zendesk API unavailable (Error code : "+ errorCode + ")\n" +
                "Please enter 'menu' to restart or anything else to close the program"); //TODO manage this
    }

    public static void resourceNotFound(){
        System.out.println("Error: the resource you were looking for wasn't found, maybe you entered a wrong id.\n");
        MainPage.quickMenu();
    }

    public static void errorDisplay(String error){
        System.out.println("Oops ! An unexpected error has occured : \n" + error +
                "\nPlease enter 'menu' to restart or anything else to close the program.\n"); //TODO manage this
    }

    public static void wrongIdInput(){
        System.out.println("Sorry, a ticket id should be digits only.\n");
        MainPage.quickMenu();
    }

    public static void wrongInput(){
        System.out.println("Sorry, the command you entered was not recognized.\n");
        MainPage.quickMenu();
    }
}
