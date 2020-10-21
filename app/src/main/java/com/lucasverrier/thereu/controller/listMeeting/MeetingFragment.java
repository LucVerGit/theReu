package com.lucasverrier.thereu.controller.listMeeting;
mport android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lucasverrier.thereu.R;
import com.lucasverrier.thereu.event.FilterEvent;
import com.lucasverrier.thereu.model.Meeting;
import com.lucasverrier.thereu.service.DI;
import com.lucasverrier.thereu.service.FilterApiService;
import com.lucasverrier.thereu.service.MeetingApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class MeetingFragment extends Fragment {


    private MeetingApiService mApiService;
    private FilterApiService mFilterService;

    private List<Meeting> mMeetingList = new ArrayList<>();

    private MeetingRecyclerViewAdapter mMeetingAdapter;
    private RecyclerView mRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getMeetingApiService();
        mFilterService = DI.getFilterApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_meeting, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        mMeetingAdapter = new MeetingRecyclerViewAdapter(mApiService.getMeetingList());
        mRecyclerView.setAdapter(mMeetingAdapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onFilteredList(FilterEvent event) {
        String mDateFilter = event.getDateChoice();
        String mPlaceFilter = event.getPlaceChoice();
        List<Meeting> meetingList = new ArrayList<>(mApiService.getMeetingList());

        boolean isDateFiltered = !mDateFilter.equals(getString(R.string.date_spinner_all));
        boolean isPlaceFiltered = !mPlaceFilter.equals(getString(R.string.place_spinner_all));

        if (isDateFiltered || isPlaceFiltered) {
            if (isDateFiltered) {
                meetingList = mFilterService.dateFilter(meetingList, mDateFilter);
            }
            if (isPlaceFiltered) {
                meetingList = mFilterService.placeFilter(meetingList, mPlaceFilter);
            }
        } else {
            meetingList = mApiService.getMeetingList();
        }
        //Update the filtered list
        mMeetingAdapter.setData(meetingList);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Meeting> meetingList = mApiService.getMeetingList();
        mMeetingAdapter.setData(meetingList);
    }


}
