package com.TicketViewer.Model;

import java.util.ArrayList;
import java.util.Map;

public class TicketList {
    public static int TicketCount = -1;
    public static ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>(); //Contains 25 tickets or less
    public static int numberOfPages = -1;

    public static int getTicketCount() {
        return TicketCount;
    }

    public static void setTicketCount(int ticketCount) {
        TicketCount = ticketCount;
    }

    public static ArrayList<Map<String, Object>> getList() {
        return list;
    }

    public static void setList(ArrayList<Map<String, Object>> list) {
        TicketList.list = list;
    }

    public static int getNumberOfPages() {
        return numberOfPages;
    }

    public static void setNumberOfPages(int numberOfPages) {
        TicketList.numberOfPages = numberOfPages;
    }
}
