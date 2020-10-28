package com.lucasverrier.thereu.utils;

import android.view.View;

import com.lucasverrier.thereu.R;

import org.hamcrest.Matcher;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

public class DeleteViewAction implements ViewAction {
    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Click on specific button";
    }

    @Override
    public void perform(UiController uiController, View view) {
        View button = view.findViewById(R.id.meeting_delete_button);
        // Maybe check for null
        button.performClick();
    }
}
