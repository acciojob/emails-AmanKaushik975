package com.driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

import java.util.*;

public class Gmail extends Email {
    private int inboxCapacity;
    private Queue<Mail> inbox;
    private Queue<Mail> trash;

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        this.inbox = new LinkedList<>();
        this.trash = new LinkedList<>();
    }

    public void receiveMail(Date date, String sender, String message) {
        if (inbox.size() >= inboxCapacity) {
            trash.add(inbox.poll());
        }
        inbox.add(new Mail(date, sender, message));
    }

    public void deleteMail(String message) {
        Mail mailToRemove = inbox.stream()
                .filter(mail -> mail.getMessage().equals(message))
                .findFirst()
                .orElse(null);

        if (mailToRemove != null) {
            inbox.remove(mailToRemove);
            trash.add(mailToRemove);
        }
    }

    public String findLatestMessage() {
        if (inbox.isEmpty()) {
            return null;
        }
        return inbox.peek().getMessage();
    }

    public String findOldestMessage() {
        if (inbox.isEmpty()) {
            return null;
        }
        return inbox.toArray(new Mail[0])[inbox.size() - 1].getMessage();
    }

    public int findMailsBetweenDates(Date start, Date end) {
        int count = 0;
        for (Mail mail : inbox) {
            if (mail.getDate().compareTo(start) >= 0 && mail.getDate().compareTo(end) <= 0) {
                count++;
            }
        }
        return count;
    }

    public int getInboxSize() {
        return inbox.size();
    }

    public int getTrashSize() {
        return trash.size();
    }

    public void emptyTrash() {
        trash.clear();
    }

    public int getInboxCapacity() {
        return inboxCapacity;
    }

    private static class Mail {
        private Date date;
        private String sender;
        private String message;

        public Mail(Date date, String sender, String message) {
            this.date = date;
            this.sender = sender;
            this.message = message;
        }

        public Date getDate() {
            return date;
        }

        public String getSender() {
            return sender;
        }

        public String getMessage() {
            return message;
        }
    }
}
