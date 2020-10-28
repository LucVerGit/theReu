package com.lucasverrier.thereu;

import com.lucasverrier.thereu.controller.listMeeting.ListMeetingActivity;
import com.lucasverrier.thereu.model.Meeting;
import com.lucasverrier.thereu.service.DI;
import com.lucasverrier.thereu.service.MeetingApiService;
import com.lucasverrier.thereu.utils.DeleteViewAction;

import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.contrib.ViewPagerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.lucasverrier.thereu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsNull.notNullValue;

public class InstrumentedTestMeetingList {


    MeetingApiService service;

    @Rule
    public ActivityTestRule<ListMeetingActivity> mActivityRule =
            new ActivityTestRule<>(ListMeetingActivity.class);

    @Before
    public void setUp() {
        ListMeetingActivity mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());

        service = DI.getMeetingApiService();
        service.getMeetingList().clear();
    }

    @Test
    public void startApp_listMustBeEmpty(){
        onView(withId(R.id.list_meeting_container)).check(matches(hasMinimumChildCount(0)));
    }

    @Test
    public void startApp_canNotUseFilter(){
        onView(withId(R.id.action_filter)).perform(click());
        onView(withId(R.id.list_meeting_container)).check(matches(hasMinimumChildCount(0)));
        onView(withId(R.id.card_filter)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }

    @Test
    public void btnAdd_shouldLaunchSecondActivity(){
        onView(withId(R.id.activity_list_meeting_fab_add)).perform(click());
        onView(withId(R.id.add_new_meeting_title)).check((matches(isDisplayed())));
    }

    @Test
    public void cancelAddMeeting_shouldBackMainActivity(){
        onView(withId(R.id.activity_list_meeting_fab_add)).perform(click());
        onView(withId(R.id.add_new_meeting_title)).check(matches(isDisplayed()));
        onView(withId(R.id.cancel_new_meeting_btn)).perform(scrollTo());
        onView(withId(R.id.cancel_new_meeting_btn)).perform(click());
        onView(withId(R.id.list_meeting_container)).check(matches(isDisplayed()));
    }

    @Test
    public void addNewMeetingAction_shouldDisplayedNewMeeting(){
        onView(withId(R.id.activity_list_meeting_fab_add)).perform(click());
        onView(withId(R.id.add_new_title_input)).perform(typeText("Test"));
        onView(withId(R.id.add_new_meeting_vp)).perform(ViewPagerActions.scrollRight());
        onView(withId(R.id.add_new_participant_input)).perform(typeText("aaa@mail.com"));
        onView(withId(R.id.add_new_participant_btn)).perform(click());
        onView(withId(R.id.add_new_meeting_vp)).perform(ViewPagerActions.scrollLeft());
        onView(withId(R.id.confirm_new_meeting_btn)).perform(scrollTo());
        onView(withId(R.id.confirm_new_meeting_btn)).perform(click());
        onView(withId(R.id.activity_list_meeting_fab_add)).check(matches(isDisplayed()));
        onView(withId(R.id.list_meeting_container)).check(withItemCount(1));
    }

    @Test
    public void deleteMeetingAction_shouldSuppressMeeting(){
        service.addNewMeeting(new Meeting("Delete", "Mario", LocalDateTime.now(), 255, new ArrayList<>()));
        onView(withId(R.id.list_meeting_container)).check(matches(isDisplayed()));
        onView(withId(R.id.list_meeting_container)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));
        onView(withId(R.id.list_meeting_container)).check(withItemCount(0));
    }

    @Test
    public void addNewMeetingAction_shouldNotConfirmIfEmpty(){
        onView(withId(R.id.activity_list_meeting_fab_add)).perform(click());
        onView(withId(R.id.confirm_new_meeting_btn)).check(matches(not(isEnabled())));
    }


}
