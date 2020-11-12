package com.TicketViewer.Controller;

import com.TicketViewer.View.MainPage;

public class Main {

    public static void main(String[] args) {

        HttpConnectionHandler httpConnectionHandler = HttpConnectionHandler.getInstance();
        httpConnectionHandler.initConnection();
        MainPage mainPage = MainPage.getInstance();
        MainPageController mainPageController = MainPageController.getInstance();
        mainPage.welcome();
        mainPageController.parseUserInput("");
    }
}
