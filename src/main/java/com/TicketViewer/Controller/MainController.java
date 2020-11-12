package com.TicketViewer.Controller;

import com.TicketViewer.View.MainPage;

public class MainController {
    public static Controller controller = MainPageController.getInstance();

    public static Controller getController() {
        return controller;
    }

    public static void setController(Controller controller) {
        MainController.controller = controller;
    }

    public static void run(String command){
        controller.control(command);
    }

}
