package com.TicketViewer.View;

public class MainPage {
    public static void welcome(){
        System.out.println("Welcome to Ticket Viewer !\n" +
                "           What would you like to do ?\n" +
                "\n" +
                "           -> Enter 1 to view all tickets\n" +
                "           -> Enter 2 to view one ticket in details\n" +
                "           -> Enter 'exit' quit close this program\n\n");
    }

    public static void quickMenu(){
        System.out.println("1: View all tickets; 2: View ticket details; 'quit': exit\n");
    }
}
