package com.TicketViewer.Controller;

import java.io.IOException;

public class ErrorController {
    public static void exitOnInputError() throws IOException {
        System.in.read();
        System.exit(-1);
    }
}
