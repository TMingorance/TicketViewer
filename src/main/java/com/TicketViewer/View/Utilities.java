package com.TicketViewer.View;

public class Utilities {

    public static void clearScreen(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
}
