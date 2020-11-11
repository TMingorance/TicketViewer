package com.TicketViewer.View;

import com.TicketViewer.Model.JsonTicket;

import java.util.Map;

public class TicketDisplay {

    private MainPage mainPage;
    private JsonTicket jsonTicket;

    private static volatile TicketDisplay ticketDisplay = new TicketDisplay();

    private TicketDisplay(){
        this.mainPage = MainPage.getInstance();
        this.jsonTicket = JsonTicket.getInstance();
    }

    public static TicketDisplay getInstance(){
        if(ticketDisplay != null) {
            return ticketDisplay;
        }
        else{
            ticketDisplay = new TicketDisplay();
            return ticketDisplay;
        }
    }

    public void whichTicket(){
        System.out.println("Please enter the id of the ticket you want to examine.");
    }

    public void display() {//display all attributes
        mainPage.clearScreen();
        Map<String,Object> ticket = ((Map<String,Object>)jsonTicket.getTicket().get("ticket"));

        System.out.println("Id: " +ticket.get("id") + "\n" +
                "Type: " + ticket.get("type") + "\n" +
                "Subject: " + ticket.get("subject") + "\n" +
                "Description: " + ticket.get("description") + "\n\n" +
                "Priority: " + ticket.get("priority") + "\n" +
                "Status: " + ticket.get("status"));
                String created_at = (String)ticket.get("created_at");
                String updated_at = (String)ticket.get("updated_at");
                created_at = formatDate(created_at);
                updated_at = formatDate(updated_at);
                System.out.println("Created at: " + created_at + "\n" +
                "Updated at: " + updated_at + "\n");

        if(ticket.get("type") == "task"){
            System.out.println("Due at: " + ticket.get("due_at") + "\n");
        }

        System.out.println("Requester_id: " + ticket.get("requester_id") + "\n" +
                "Organization_id: " + ticket.get("organization_id") + "\n" +
                "Submitter_id: " + ticket.get("submitter_id") + "\n" +
                "Recipient: " + ticket.get("recipient") + "\n" +
                "Assignee_id: " + ticket.get("assignee_id") + "\n");

        if(ticket.get("type") == "incident") {
            System.out.println("Problem_id: " + ticket.get("problem_id") + "\n");
        }

        System.out.println("Group_id: " + ticket.get("group_id") + "\n" +
                "Collaborators_ids: " + ticket.get("collaborators_ids") + "\n" +
                "Ids of emails in CC: " + ticket.get("email_cc_ids") + "\n" +
                "Follower_ids: " + ticket.get("follower_ids") + "\n\n" +
                "Forum_topic_id: " + ticket.get("forum_topic_id") + "\n" +
                "Made via: " + ((Map<String, Object>)ticket.get("via")).get("channel") + "\n\n" +
                "Tags: " + ticket.get("tags") + "\n\n" +
                "Satisfaction rating: " + ticket.get("satisfaction_rating") + "\n\n" +
                "Sharing agreement ids: " + ticket.get("sharing_agreement_ids") + "\n" +
                "Followup_ids: " + ticket.get("followup_ids") + "\n\n" +
                "Ticket form id: " + ticket.get("ticket_form_id") + "\n" +
                "Brand id: " + ticket.get("brand_id") + "\n\n" +
                "Channelback allowed? " + ticket.get("allow_channelback") + "\n" +
                "Attachments allowed? " + ticket.get("allow_attachments") + "\n" +
                "Public? " + ticket.get("is_public") + "\n");
        mainPage.quickMenu();
    }

    private String formatDate(String date){
        StringBuilder stringBuilder = new StringBuilder(date);
        stringBuilder.setCharAt(10, ' ');
        stringBuilder.deleteCharAt(19);
        return stringBuilder.toString();
    }
}
