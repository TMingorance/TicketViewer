package com.TicketViewer.Controller;

import com.TicketViewer.View.ErrorDisplay;
import com.TicketViewer.View.MainPage;

public class ErrorManager {

    private static ErrorManager errorManager;

    private ErrorManager(){}

    public static ErrorManager getInstance(){
        if(errorManager != null) {
            return errorManager;
        }
        else{
            errorManager = new ErrorManager();
            return errorManager;
        }
    }

    private ErrorDisplay errorDisplay = ErrorDisplay.getInstance();

    protected void manageUnavailableAPIException(int errorCode){
        errorDisplay.unavailableAPIDisplay(errorCode);
        MainController.setController(MainPageController.getInstance());
        MainController.run("");
    }

    protected void manageError(String error){
        errorDisplay.errorDisplay(error);
        MainController.setController(MainPageController.getInstance());
        MainController.run("");
    }

    protected void wrongInputMainMenu(){
        errorDisplay.wrongInputMainMenu();
        MainController.setController(MainPageController.getInstance());
        MainController.run("");
    }

    protected void manageResourceNotFound(){
        errorDisplay.resourceNotFound();
        MainController.setController(MainPageController.getInstance());
        MainController.run("");
    }

    protected void wrongInputPageMenu(){
        errorDisplay.wrongInputPageMenu();
        MainController.run("page error recover");
    }

    protected void wrongIdInput(){
        errorDisplay.wrongIdInput();
        MainController.setController(MainPageController.getInstance());
        MainController.run("");
    }

}
