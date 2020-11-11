package com.TicketViewer.Controller;

import com.TicketViewer.View.ErrorDisplay;
import com.TicketViewer.View.MainPage;

public class ErrorManager {

    private static ErrorDisplay errorDisplay = ErrorDisplay.getInstance();

    protected static void manageUnavailableAPIException(int errorCode){
        errorDisplay.unavailableAPIDisplay(errorCode);
    }

    protected static void manageError(String error){
        errorDisplay.errorDisplay(error);
        MainPageUI.parseUserInput("");
    }

    protected static void wrongInputMainMenu(){
        errorDisplay.wrongInputMainMenu();
        MainPageUI.parseUserInput("");
    }

    protected static void manageResourceNotFound(){
        errorDisplay.resourceNotFound();
        MainPageUI.parseUserInput("");
    }

}
