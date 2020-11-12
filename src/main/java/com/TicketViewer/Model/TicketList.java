package com.TicketViewer.Model;

import java.util.ArrayList;
import java.util.Map;

public class TicketList {

    private static volatile TicketList ticketList = new TicketList();

    private TicketList(){}

    public static TicketList getInstance(){
        if(ticketList != null) {
            return ticketList;
        }
        else{
            ticketList = new TicketList();
            return ticketList;
        }
    }

    public int ticketCount = -1;
    public ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>(); //Contains 25 tickets or less
    public int numberOfPages = -1;
    public int currentPage = 0;
    public boolean hasMore = false;
    public String prevPageUrl = "";
    public String nextPageUrl = "";

    public int getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }

    public ArrayList<Map<String, Object>> getList() {
        return list;
    }

    public void setList(ArrayList<Map<String, Object>> list) {
        this.list = list;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public String getPrevPageUrl() {
        return prevPageUrl;
    }

    public void setPrevPageUrl(String prevPageUrl) {
        this.prevPageUrl = prevPageUrl;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
