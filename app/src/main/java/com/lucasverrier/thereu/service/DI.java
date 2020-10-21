package com.lucasverrier.thereu.service;

public class DI {

    private static MeetingApiService meetingService = new MeetingService();
    private static FilterApiService filterService = new FilterService();
    public static MeetingApiService getMeetingApiService() {
        return meetingService;
    }
    public static FilterApiService getFilterApiService(){
        return filterService;
    }

}