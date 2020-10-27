package com.lucasverrier.thereu;


import android.content.res.Resources;

import com.lucasverrier.thereu.controller.listMeeting.ListMeetingActivity;
import com.lucasverrier.thereu.model.Meeting;
import com.lucasverrier.thereu.service.DI;
import com.lucasverrier.thereu.service.MeetingApiService;

import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Rule;

import java.util.ArrayList;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

public class InstrumentedTestFilterDate {

    Resources res = InstrumentationRegistry.getInstrumentation().getTargetContext().getResources();
    MeetingApiService service = DI.getMeetingApiService();

    @Rule
    public ActivityTestRule<ListMeetingActivity> mActivityRule =
            new ActivityTestRule<>(ListMeetingActivity.class, true, true);

    @Before
    public void setup() {
        ListMeetingActivity mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());

        service.getMeetingList().clear();

        Meeting today = new Meeting("Today", res.getString(R.string.place_spinner_mario), LocalDateTime.now(), 1, new ArrayList<>());
        Meeting tomorrow = new Meeting("Tomorrow", res.getString(R.string.place_spinner_mario), LocalDateTime.now().plusDays(1), 2, new ArrayList<>());
        Meeting nextWeek = new Meeting("Next Week", res.getString(R.string.place_spinner_mario), LocalDateTime.now().plusDays(7), 3, new ArrayList<>());
        Meeting nextMonth = new Meeting("Next Month", res.getString(R.string.place_spinner_mario), LocalDateTime.now().plusMonths(1), 4, new ArrayList<>());

        service.addNewMeeting(today);
        service.addNewMeeting(tomorrow);
        service.addNewMeeting(nextWeek);
        service.addNewMeeting(nextMonth);
    }


}
