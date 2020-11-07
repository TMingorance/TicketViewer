package com.TicketViewer.Model;

import org.zendesk.client.v2.Zendesk;

public class ZendeskAPI {
    public static Zendesk instance = null;

    public static Zendesk getInstance(){
        if (ZendeskAPI.instance == null) {
            ZendeskAPI.instance = new Zendesk.Builder("https://enssat.zendesk.com")
                    .setUsername("tmingora@enssat.fr")
                    .setPassword("123456")
                    .build();
            return ZendeskAPI.instance;
        }
        else{
            return ZendeskAPI.getInstance();
        }
    }

    public static void closeInstance(){
        if(instance != null){
            instance.close();
        }
    }
}
