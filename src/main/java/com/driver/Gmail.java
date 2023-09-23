package com.driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;



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
            // Inbox is full, move the oldest mail to trash
            trash.add(inbox.poll());
        }
        inbox.add(new Mail(date, sender, message));
    }

    public void deleteMail(String message) {
        // Search for the message in the inbox and move it to trash if found
        inbox.removeIf(mail -> mail.getMessage().equals(message));
    }

    public String findLatestMessage() {
        if (inbox.isEmpty()) {
            return null; // Inbox is empty
        }
        return inbox.peek().getMessage(); // Latest message is at the front of the inbox
    }

    public String findOldestMessage() {
        if (inbox.isEmpty()) {
            return null; // Inbox is empty
        }
        return inbox.toArray(new Mail[0])[inbox.size() - 1].getMessage(); // Oldest message is at the back of the inbox
    }

    public int findMailsBetweenDates(Date start, Date end) {
        int count = 0;
        for (Mail mail : inbox) {
            Date mailDate = mail.getDate();
            if (mailDate.compareTo(start) >= 0 && mailDate.compareTo(end) <= 0) {
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
        trash.clear(); // Clear all mails in the trash
    }

    public int getInboxCapacity() {
        return inboxCapacity;
    }

    private static class Mail {
        private Date date;
        private String senderId;
        private String message;

        public Mail(Date date, String senderId, String message) {
            this.date = date;
            this.senderId = senderId;
            this.message = message;
        }

        public Date getDate() {
            return date;
        }

        public String getSenderId() {
            return senderId;
        }

        public String getMessage() {
            return message;
        }
    }
}
