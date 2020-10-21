package com.lucasverrier.thereu.service;

import java.util.List;

public interface MeetingApiService {
    List<Meeting> getMeetingList();
    void addNewMeeting(Meeting meeting);
    void deleteMeeting(Meeting meeting);

}
