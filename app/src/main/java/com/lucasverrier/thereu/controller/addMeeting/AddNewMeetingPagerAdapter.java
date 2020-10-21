package com.lucasverrier.thereu.controller.addMeeting;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class AddNewMeetingPagerAdapter extends FragmentPagerAdapter {
    public AddNewMeetingPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0 :
                return InformationFragment.newInstance();
            case 1:
                return ParticipantFragment.newInstance();
            default :
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
