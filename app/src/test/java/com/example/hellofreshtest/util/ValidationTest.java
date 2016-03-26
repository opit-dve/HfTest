package com.example.hellofreshtest.util;

import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by srd on 11/22/2015.
 */
@SmallTest
public class ValidationTest {

    @Test
    public void validation_CorrectEmailSimple_ReturnsTrue() {
        assertTrue(Validation.isValidEmail2("name@email.com"));
    }

    @Test
    public void validation_CorrectEmailSubDomain_ReturnTrue() {
        assertTrue(Validation.isValidEmail2("name@email.co.uk"));
    }

    @Test
    public void validation_InvalidEmailNoTld_ReturnsFalse() {
        assertFalse(Validation.isValidEmail2("name@email"));
    }

    @Test
    public void validation_InvalidEmailDoubleDot_ReturnsFalse() {
        assertFalse(Validation.isValidEmail2("name@email..com"));
    }

    @Test
    public void validation_InvalidEmailNoUsername_ReturnsFalse() {
        assertFalse(Validation.isValidEmail2("@email.com"));
    }

    @Test
    public void validation_EmptyString_ReturnsFalse() {
        assertFalse(Validation.isValidEmail2(""));
    }

    @Test
    public void validation_NullEmail_ReturnsFalse() {
        assertFalse(Validation.isValidEmail2(null));
    }
}
