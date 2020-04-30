package edu.quinnipiac.ser210.fevertracker;

import android.view.View;
import android.widget.ListView;
import android.widget.SeekBar;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.PickerActions.setDate;
import static androidx.test.espresso.contrib.PickerActions.setTime;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private int total;

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void addRecord() {
        onView(withId(R.id.button_first)).perform(click());
        onView(withId(R.id.txtTemp)).perform(typeText("98.6"), ViewActions.closeSoftKeyboard());
        String expectedTemp = "Temperature: 98.6";
        onView(withId(R.id.date_picker)).perform(scrollTo()).perform(setDate(2020, 4, 30));
        String expectedDate = "Date: 04-30-2020";
        onView(withId(R.id.timePicker)).perform(scrollTo()).perform(setTime(12, 0));
        String expectedTime = "Time: 12:00 PM";
        onView(withId(R.id.seekBar)).perform(scrollTo()).perform(setProgress(8));
        String expectedFeeling = "Feeling: 9"; // The actual value is offset by 1 in the SeekBar because it always starts at 0
        onView(withId(R.id.btnNext)).perform(scrollTo()).perform(click());

        getTotal();
        onData(anything()).inAdapterView(withId(R.id.listReports)).atPosition(total-1).perform(click());

        onView(withId(R.id.txtTemp)).check(ViewAssertions.matches(withText(expectedTemp)));
        onView(withId(R.id.txtDate)).check(ViewAssertions.matches(withText(expectedDate)));
        onView(withId(R.id.txtTime)).check(ViewAssertions.matches(withText(expectedTime)));
        onView(withId(R.id.txtFeeling)).check(ViewAssertions.matches(withText(expectedFeeling)));

    }

    public void getTotal() {
        onView(withId(R.id.listReports)).check(matches(new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {}

            @Override
            protected boolean matchesSafely(View item) {
                ListView listView = (ListView) item;
                total = listView.getCount();
                return true;
            }
        }));
    }

    public static ViewAction setProgress(final int progress) {
        return new ViewAction() {

            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(SeekBar.class);
            }

            @Override
            public String getDescription() {
                return "";
            }

            @Override
            public void perform(UiController uiController, View view) {
                SeekBar seekBar = (SeekBar) view;
                seekBar.setProgress(progress);
            }
        };
    }
}
