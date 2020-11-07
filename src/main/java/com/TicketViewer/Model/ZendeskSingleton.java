package com.TicketViewer.Model;

import org.zendesk.client.v2.Zendesk;

public class ZendeskSingleton {
    public static Zendesk instance = null;

    public static Zendesk getInstance(){//TODO : gérer les erreurs
        if (ZendeskSingleton.instance == null) {
            ZendeskSingleton.instance = new Zendesk.Builder("https://enssat.zendesk.com")
                    .setUsername("tmingora@enssat.fr")
                    .setPassword("123456")
                    .build();
            return ZendeskSingleton.instance;
        }
        else{
            return ZendeskSingleton.getInstance();
        }
    }

    public static void closeInstance(){
        if(instance != null){
            instance.close();
        }
    }
}
