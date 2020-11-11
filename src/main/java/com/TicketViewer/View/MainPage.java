package com.TicketViewer.View;

public class MainPage {
    public static void welcome(){
        System.out.println("Welcome to Ticket Viewer !\n" +
                "           What would you like to do ?\n" +
                "\n" +
                "           -> Enter 'a' to view all tickets\n" +
                "           -> Enter 'd' to view one ticket in details\n" +
                "           -> Enter 'exit' quit close this program\n\n");
    }

    public static void quickMenu(){
        System.out.println("a: View all tickets; d: View ticket details; quit: exit\n");
    }

    public static void clearScreen(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
}
