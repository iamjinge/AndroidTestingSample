package com.jinge.helloworld;

import android.os.Handler;
import android.os.Looper;
import android.support.test.internal.runner.junit4.statement.UiThreadStatement;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Android Unit Tests for {@link MainActivity}
 */
@RunWith(AndroidJUnit4.class)
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

    /**
     * The default Test Thread is not UI Thread.
     * Use {@link ActivityTestRule#runOnUiThread(Runnable)}
     * of {@link UiThreadStatement#runOnUiThread(Runnable)}
     * for UI operations.
     * These methods ensure the Runnable run to the end.
     * Test Thread will not continue before the Runnable end.
     * <p>
     * Output is :
     * <pre>
     * waiting
     * end UI Thread
     * end
     * </pre>
     */
    @Test
    public void testUiThread() throws Throwable {
        System.out.println("waiting");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end UI Thread");
            }
        });
        System.out.println("end");
    }

    /**
     * If you use a custom {@link Handler} to post ui operations.
     * You should use lock to ensure the execution order.
     * This trick can also be useful when you run on other Threads.
     */
    @Test
    public void testCustomUiThread() throws InterruptedException {
        final Object mutex = new Object();
        Handler mainHandler = new Handler(Looper.getMainLooper());
        System.out.println("waiting");
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (mutex) {
                    mutex.notify();
                }
                System.out.println("end UI Thread");
            }
        });
        synchronized (mutex) {
            mutex.wait();
        }
        System.out.println("end");
    }
}
