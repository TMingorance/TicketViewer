package com.TicketViewer.View;

import com.TicketViewer.Model.JsonTicket;
import org.zendesk.client.v2.model.Ticket;

import java.io.IOException;

public class TicketDisplay {

    public static void whichTicket(){
        System.out.println("Please enter the id of the ticket you want to examine.\n");
    }
    public static void display() throws IOException {//display all attributes
        Runtime.getRuntime().exec("cls");
        System.out.println("Id: " +JsonTicket.getTicket().get("id") + "\n" +
                "Type: " + JsonTicket.getTicket().get("type") + "\n" +
                "Subject: " + JsonTicket.getTicket().get("subject") + "\n" +
                "Description: " + JsonTicket.getTicket().get("description") + "\n\n" +
                "Priority: " + JsonTicket.getTicket().get("priority") + "\n" +
                "Status: " + JsonTicket.getTicket().get("status") + "\n" +
                "Created at: " + JsonTicket.getTicket().get("created_at") + "\n" +
                "Updated at: " + JsonTicket.getTicket().get("updated_at") + "\n\n");

        if(JsonTicket.getTicket().get("type") == "task"){
            System.out.println("Due at: " + JsonTicket.getTicket().get("due_at") + "\n\n");
        }

        System.out.println("Requester_id: " + JsonTicket.getTicket().get("requester_id") + "\n" +
                "Organization_id" + JsonTicket.getTicket().get("organization_id") + "\n" +
                "Submitter_id: " + JsonTicket.getTicket().get("submitter_id") + "\n" +
                "Recipient: " + JsonTicket.getTicket().get("recipient") + "\n" +
                "Assignee_id: " + JsonTicket.getTicket().get("assignee_id") + "\n\n");

        if(JsonTicket.getTicket().get("type") == "incident") {
            System.out.println("Problem_id: " + JsonTicket.getTicket().get("problem_id") + "\n\n");
        }

        System.out.println("Group_id: " + JsonTicket.getTicket().get("group_id") + "\n" +
                "Collaborators_ids: " + JsonTicket.getTicket().get("collaborators_ids") + "\n" +
                "Ids of emails in CC: " + JsonTicket.getTicket().get("email_cc_ids") + "\n" +
                "Follower_ids: " + JsonTicket.getTicket().get("follower_ids") + "\n\n" +
                "Forum_topic_id: " + JsonTicket.getTicket().get("forum_topic_id") + "\n" +
                "Made via: " + JsonTicket.getTicket().get("via") + "\n\n" +
                "Tags: " + JsonTicket.getTicket().get("tags") + "\n\n" +
                "Satisfaction rating: " + JsonTicket.getTicket().get("satisfaction_rating") + "\n\n" +
                "Sharing agreement ids: " + JsonTicket.getTicket().get("sharing_agreement_ids") + "\n" +
                "Followup_ids: " + JsonTicket.getTicket().get("followup_ids") + "\n\n" +
                "Ticket form id: " + JsonTicket.getTicket().get("ticket_form_id") + "\n" +
                "Brand id: " + JsonTicket.getTicket().get("brand_id") + "\n\n" +
                "Channelback allowed? " + JsonTicket.getTicket().get("allow_channelback") + "\n" +
                "Attachments allowed? " + JsonTicket.getTicket().get("allow_attachments") + "\n" +
                "Public? " + JsonTicket.getTicket().get("is_public") + "\n");
    }
}
