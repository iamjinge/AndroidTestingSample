package com.jinge.helloworld;

import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by Elton on 2018/1/25.
 */
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);
    private TextView textView;

    @Before
    public void init() {
        textView = activityRule.getActivity().findViewById(R.id.text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Test
    public void testContent() {
        onView(withId(R.id.text))
                .check(matches(withText(R.string.hello_world)));
    }

    @Test
    public void testShow() throws Throwable {
        onView(equalTo((View) textView))
                .perform(click())
                .check(matches(not(isDisplayed())));
    }
}