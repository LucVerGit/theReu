package com.lucasverrier.thereu.service;

import java.util.ArrayList;
import java.util.List;

public class FilterService implements FilterApiService {

    public List<Meeting> placeFilter(List<Meeting> meetingList, String place) {
        List<Meeting> toRemoveList = new ArrayList<>();
        for (Meeting meeting : meetingList) {
            if (!meeting.getMeetingPlace().equals(place)) {
                toRemoveList.add(meeting);
            }
        }
        meetingList.removeAll(toRemoveList);
        return meetingList;
    }



}
