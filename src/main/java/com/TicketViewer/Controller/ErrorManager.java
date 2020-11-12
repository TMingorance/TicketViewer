package com.TicketViewer.Controller;

import com.TicketViewer.View.ErrorDisplay;
import com.TicketViewer.View.MainPage;

public class ErrorManager {

    private static ErrorDisplay errorDisplay = ErrorDisplay.getInstance();
    private static MainPageController mainPageController = MainPageController.getInstance();

    protected static void manageUnavailableAPIException(int errorCode){
        errorDisplay.unavailableAPIDisplay(errorCode);
        MainController.setController(MainPageController.getInstance());
        MainController.run("");
    }

    protected static void manageError(String error){
        errorDisplay.errorDisplay(error);
        MainController.setController(MainPageController.getInstance());
        MainController.run("");
    }

    protected static void wrongInputMainMenu(){
        errorDisplay.wrongInputMainMenu();
        MainController.setController(MainPageController.getInstance());
        MainController.run("");
    }

    protected static void manageResourceNotFound(){
        errorDisplay.resourceNotFound();
        MainController.setController(MainPageController.getInstance());
        MainController.run("");
    }

    protected static void wrongInputPageMenu(){
        errorDisplay.wrongInputPageMenu();
        MainController.run("page error recover");
    }

    protected static void wrongIdInput(){
        errorDisplay.wrongIdInput();
        MainController.setController(MainPageController.getInstance());
        MainController.run("");
    }

}
