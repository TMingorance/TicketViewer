package com.TicketViewer.Controller;

import com.TicketViewer.View.ErrorDisplay;

public class ErrorManager {

    private static ErrorDisplay errorDisplay = ErrorDisplay.getInstance();

    protected static void manageUnavailableAPIException(int errorCode){
        errorDisplay.unavailableAPIDisplay(errorCode);
        MainPageController.parseUserInput("");
    }

    protected static void manageError(String error){
        errorDisplay.errorDisplay(error);
        MainPageController.parseUserInput("");
    }

    protected static void wrongInputMainMenu(){
        errorDisplay.wrongInputMainMenu();
        MainPageController.parseUserInput("");
    }

    protected static void manageResourceNotFound(){
        errorDisplay.resourceNotFound();
        MainPageController.parseUserInput("");
    }

}
