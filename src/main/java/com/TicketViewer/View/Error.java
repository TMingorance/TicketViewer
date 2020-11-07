package com.TicketViewer.View;

import java.io.IOException;

public class Error {
    public static void unavailableAPIDisplay() throws IOException {
        System.out.println("Error : Zendesk API unavailable\n" +
                "Please enter 'menu' to restart or 'exit' to close the program");
    }
    public static void errorDisplay(String error){
        System.out.println("Oops ! An unexpected error has occured : \n" + error);
    }
}
