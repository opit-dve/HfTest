package com.example.hellofreshtest;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 *
 * <a href="http://developer.android.com/training/testing/ui-testing/index.html">UI Tests</a>
 *
 * <a href="http://developer.android.com/training/testing/unit-testing/local-unit-tests.html">Local Unit Tests</a>
 * <a href="http://developer.android.com/training/testing/unit-testing/instrumented-unit-tests.html">Instrumented Unit Tests</a>
 *
 * <a href="https://google.github.io/android-testing-support-library/">ATSL Android Test Support Library</a>
 * <a href="https://github.com/googlesamples/android-testing">Android Testing Samples</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
}