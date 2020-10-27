package com.lucasverrier.thereu;


import android.content.res.Resources;

import com.lucasverrier.thereu.controller.listMeeting.ListMeetingActivity;
import com.lucasverrier.thereu.model.Meeting;
import com.lucasverrier.thereu.service.DI;
import com.lucasverrier.thereu.service.MeetingApiService;

import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static com.lucasverrier.thereu.utils.RecyclerViewItemCountAssertion.withItemCount;

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

    @Test
    public void dateFilterAction_today(){
        //Click on filter tab item
        onView(withId(R.id.action_filter)).perform(click());
        onView(withId(R.id.card_filter)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        //Click on spinner item "Today"
        onView(withId(R.id.date_spinner)).perform(click());
        onData(is(res.getString(R.string.date_spinner_today))).perform(click());
        //Check if the correct meeting is displayed
        onView(withId(R.id.list_meeting_container)).check(withItemCount(1));
        onView(withText("Today")).check(matches(isDisplayed()));
        onView(withText("Tomorrow")).check(doesNotExist());
        onView(withText("Next Week")).check(doesNotExist());
        onView(withText("Next Month")).check(doesNotExist());
    }

}
