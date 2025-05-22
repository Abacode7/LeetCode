package com.company;

public class LC1472_DesignBrowserHistory {
    public static void main(String[] args){
        System.out.println("Hello world!");
    }
}

class BrowserHistory {
    /**
     Using a doubly linked list
     a b c f | d e

     Runtime:
     visit: O(1)
     back: O(k), where k is the number of backward steps
     forward: O(k), where k is the number of forward steps

     Space: O(N), where N is the total number of urls visited

     Tags: Doubly Linked List
     **/
    private BNode currentPage;

    public BrowserHistory(String homepage) {
        this.currentPage = new BNode(homepage);
    }

    public void visit(String url) {
        BNode nextPage = new BNode(url);
        nextPage.prev = currentPage;
        if(currentPage != null){
            currentPage.next = nextPage;
        }

        currentPage = nextPage;
    }

    public String back(int steps) {
        int step = 0;
        while(currentPage.prev != null && step != steps){
            currentPage = currentPage.prev;
            step++;
        }
        return currentPage.value;
    }

    public String forward(int steps) {
        int step = 0;
        while(currentPage.next != null && step != steps){
            currentPage = currentPage.next;
            step++;
        }
        return currentPage.value;
    }
}

class BNode {
    String value;
    BNode prev;
    BNode next;

    public BNode(String value){
        this.value = value;
    }
}