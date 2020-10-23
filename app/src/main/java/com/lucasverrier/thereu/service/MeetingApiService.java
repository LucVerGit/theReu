package com.lucasverrier.thereu.service;

import com.lucasverrier.thereu.model.Meeting;

import java.util.List;

public interface MeetingApiService {
    List<Meeting> getMeetingList();
    void addNewMeeting(Meeting meeting);
    void deleteMeeting(Meeting meeting);

}
