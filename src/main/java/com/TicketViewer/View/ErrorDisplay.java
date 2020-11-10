package com.TicketViewer.View;

import java.io.IOException;

public class ErrorDisplay {
    public static void unavailableAPIDisplay(int errorCode) throws IOException {
        System.out.println("Error : Zendesk API unavailable (Error code : "+ errorCode + ")\n" +
                "Please enter 'menu' to restart or 'exit' to close the program");
    }
    public static void errorDisplay(String error){
        System.out.println("Oops ! An unexpected error has occured : \n" + error +
                "\n please enter menu to restart or 'exit' to close the program.\n");

    }
}
