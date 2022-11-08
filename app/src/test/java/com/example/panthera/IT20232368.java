package com.example.panthera;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.panthera.specialist.TestMethods;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class IT20232368 {

    private static TestMethods testMethods;
    private static boolean isNull;
    private static boolean isTrue;
    private static boolean nameValidate;

    @BeforeClass
    public static void objCreate() {
        testMethods = new TestMethods();
    }

    @Before
    public void setUp() {
        isTrue = false;
        isNull = false;
        nameValidate = false;
    }

    @Test
    public void NameValidate() {
        nameValidate = testMethods.validateName("Elephant", "Loxodonta");
        assertTrue(String.valueOf(true), nameValidate);
    }

//    @Test
//    public void NullDetails() {
//        isNull = testMethods.nullDetails("Elephant", "Loxodonta", "LoxodontaLoxodontaLoxodontaLoxodontaLoxodonta");
//        assertFalse(String.valueOf(false), isNull);
//    }

    @After
    public void clear() {
        isTrue = Boolean.parseBoolean(null);
        isNull = Boolean.parseBoolean(null);
        nameValidate = Boolean.parseBoolean(null);
    }

    @AfterClass
    public static void clearAll() {
        testMethods = null;
    }
}
