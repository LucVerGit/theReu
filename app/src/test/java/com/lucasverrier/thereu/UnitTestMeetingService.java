package com.lucasverrier.thereu;

import com.lucasverrier.thereu.model.Meeting;
import com.lucasverrier.thereu.service.DI;
import com.lucasverrier.thereu.service.MeetingApiService;

import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UnitTestMeetingService {
    MeetingApiService service;

    @Before
    public void setup(){
        service = DI.getMeetingApiService();
        service.getMeetingList().clear();
    }

    @Test
    public void serviceListShouldBeEmptyAtStart(){
        List<Meeting> meetingList = service.getMeetingList();
        assertTrue(meetingList.isEmpty());
    }

    @Test
    public void addMeetingWithSuccess(){
        Meeting meetingToAdd = new Meeting("Title","Mario", LocalDateTime.now(),1, new ArrayList<>());
        service.addNewMeeting(meetingToAdd);
        assertTrue(service.getMeetingList().contains(meetingToAdd));
    }

    @Test
    public void deleteMeetingWithSuccess(){
        Meeting meetingToDelete = new Meeting("Title","Mario", LocalDateTime.now(),1, new ArrayList<>());
        addMeetingWithSuccess();
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeetingList().contains(meetingToDelete));
    }
}
