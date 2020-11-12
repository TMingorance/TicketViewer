package com.TicketViewer.View;

public class ErrorDisplay {

    private MainPage mainPage;
    private TicketListDisplay ticketListDisplay;

    private static volatile ErrorDisplay errorDisplay = new ErrorDisplay();

    private ErrorDisplay(){
        this.mainPage = MainPage.getInstance();
        this.ticketListDisplay = TicketListDisplay.getInstance();
    }

    public static ErrorDisplay getInstance(){
        if(errorDisplay != null) {
            return errorDisplay;
        }
        else{
            errorDisplay = new ErrorDisplay();
            return errorDisplay;
        }
    }

    public void unavailableAPIDisplay(int errorCode){
        System.out.println("Error : Zendesk API unavailable (Error code : "+ errorCode + ")");
    }

    public void resourceNotFound(){
        System.out.println("Error: the resource you were looking for wasn't found, maybe you entered a wrong id.");
    }

    public void errorDisplay(String error){
        System.out.println("Oops ! An unexpected error has occured : \n" + error);
    }

    public void wrongIdInput(){
        System.out.println("Sorry, a ticket id should be digits only.");
    }

    public void wrongInputMainMenu(){
        System.out.println("Sorry, the command you entered was not recognized.");
    }

    public void wrongInputPageMenu(){
        System.out.println("Sorry, the command you entered was not recognized.");
        ticketListDisplay.pageCommandsIndications();
    }
}
