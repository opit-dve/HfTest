package com.example.hellofreshtest;

import android.support.design.internal.NavigationMenuPresenter;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.widget.EditText;

import com.example.hellofreshtest.ui.activity.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isSelected;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.internal.util.Checks.checkNotNull;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginTest {

    /**
     * A JUnit {@link Rule @Rule} to launch your activity under test. This is a replacement
     * for {@link ActivityInstrumentationTestCase2}.
     */
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(
            MainActivity.class);

    @Test
    public void NavigationDrawer_Open() {

        /*onView(withContentDescription("Navigate up")).perform(click());
        // check navigation is open
        onView(withId(R.id.nav_view)).check(matches(isDisplayed()));

        // select login
        //onData(allOf(is(instanceOf(NavigationMenuPresenter.NavigationMenuItem.class)), is("Login"))).perform(click());
        onView(withText("Login")).perform(click());

        // check navigation is closed
        onView(withId(R.id.nav_view)).check(matches(not(isDisplayed())));*/

        mActivityRule.getActivity().processMenuClick(R.id.menu_login);

        // ensure current fragment is 'Login'
        onView(withId(R.id.email)).check(matches(isDisplayed()));

        onView(withId(R.id.email)).perform(typeText("email@"), closeSoftKeyboard());

        onView(withId(R.id.submit)).perform(click());

        onView(withId(R.id.email)).check(matches(hasErrorText(
                mActivityRule.getActivity().getString(R.string.error_invalid_email_address))));
    }

    private static Matcher<? super View> hasErrorText(String expectedError) {
        return new ErrorTextMatcher(expectedError);
    }

    /**
     * Custom EditText error matcher
     */
    private static class ErrorTextMatcher extends TypeSafeMatcher<View> {

        private String expectedError;

        private ErrorTextMatcher(String expectedError) {
            this.expectedError = checkNotNull(expectedError);
        }

        @Override
        protected boolean matchesSafely(View view) {
            if (!(view instanceof EditText)) {
                return false;
            }

            EditText editText = (EditText)view;
            return expectedError.equals(editText.getError());
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("with error: " + expectedError);
        }
    }
}
