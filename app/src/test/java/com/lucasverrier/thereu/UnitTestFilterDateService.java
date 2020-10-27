package com.lucasverrier.thereu;

import com.lucasverrier.thereu.model.Meeting;
import com.lucasverrier.thereu.service.DI;
import com.lucasverrier.thereu.service.FilterApiService;
import com.lucasverrier.thereu.service.MeetingApiService;

import org.joda.time.LocalDateTime;
import org.junit.Before;

import java.util.ArrayList;

public class UnitTestFilterDateService {
    MeetingApiService meetingService;
    FilterApiService filterService;

    @Before
    public void setup() {
        meetingService = DI.getMeetingApiService();
        meetingService.addNewMeeting(new Meeting("Today", "Mario", LocalDateTime.now(), 1, new ArrayList<>()));
        meetingService.addNewMeeting(new Meeting("Tomorrow", "Mario", LocalDateTime.now().plusDays(1), 2, new ArrayList<>()));
        meetingService.addNewMeeting(new Meeting("Next Week", "Mario", LocalDateTime.now().plusDays(8), 3, new ArrayList<>()));
        meetingService.addNewMeeting(new Meeting("Next Month", "Mario", LocalDateTime.now().plusMonths(1).plusDays(1), 4, new ArrayList<>()));
    }
}
