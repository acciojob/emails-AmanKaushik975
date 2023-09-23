package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


import java.util.*;

public class Workspace extends Gmail {
    private ArrayList<Meeting> calendar;

    public Workspace(String emailId) {
        super(emailId, Integer.MAX_VALUE);
        calendar = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting) {
        calendar.add(meeting);
    }

    public int findMaxMeetings() {
        calendar.sort(Comparator.comparing(Meeting::getEndTime));

        int maxMeetings = 0;
        LocalTime currentEndTime = LocalTime.MIN;

        for (Meeting meeting : calendar) {
            if (meeting.getStartTime().isAfter(currentEndTime)) {
                maxMeetings++;
                currentEndTime = meeting.getEndTime();
            }
        }

        return maxMeetings;
    }
}
