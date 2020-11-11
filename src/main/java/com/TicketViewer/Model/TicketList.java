package com.TicketViewer.Model;

import java.util.ArrayList;
import java.util.Map;

public class TicketList {
    public static int TicketCount = -1;
    public static ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>(); //Contains 25 tickets or less
    public static int numberOfPages = -1;
    public static int currentPage = 0;
    public static boolean hasMore = false;
    public static String prevPageUrl = "";
    public static String nextPageUrl = "";

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

    public static boolean isHasMore() {
        return hasMore;
    }

    public static void setHasMore(boolean hasMore) {
        TicketList.hasMore = hasMore;
    }

    public static String getPrevPageUrl() {
        return prevPageUrl;
    }

    public static void setPrevPageUrl(String prevPageUrl) {
        TicketList.prevPageUrl = prevPageUrl;
    }

    public static String getNextPageUrl() {
        return nextPageUrl;
    }

    public static void setNextPageUrl(String nextPageUrl) {
        TicketList.nextPageUrl = nextPageUrl;
    }

    public static int getCurrentPage() {
        return currentPage;
    }

    public static void setCurrentPage(int currentPage) {
        TicketList.currentPage = currentPage;
    }
}
