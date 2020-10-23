package com.lucasverrier.thereu.service;

import com.lucasverrier.thereu.model.Meeting;

import java.util.List;

public interface FilterApiService {
    List<Meeting> placeFilter(List<Meeting> meetingList, String place);


    List<Meeting> dateFilter(List<Meeting> meetingList, String date);
}
