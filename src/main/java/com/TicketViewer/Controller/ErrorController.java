package com.TicketViewer.Controller;

import com.TicketViewer.Main;
import com.TicketViewer.View.MainPage;

import java.io.IOException;
import java.util.Scanner;

public class ErrorController {
    public static void exitOrRestartOnInputError() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("menu")){
            MainPage.welcome();
            MainPageUI.parseUserInput("");
        }
        else {
            System.exit(-1);
        }
    }
}
