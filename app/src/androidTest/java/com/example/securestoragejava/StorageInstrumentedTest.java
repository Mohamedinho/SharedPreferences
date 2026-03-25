package com.example.securestoragejava;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.securestoragejava.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class StorageInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testSaveAndLoadPrefs() {
        String testName = "Alice";
        String testToken = "Secret123";

        // Nettoyer avant
        onView(withId(R.id.btnClear)).perform(click());

        // 1. Entrer les données
        onView(withId(R.id.etName)).perform(typeText(testName), closeSoftKeyboard());
        onView(withId(R.id.etToken)).perform(typeText(testToken), closeSoftKeyboard());

        // 2. Sauvegarder
        onView(withId(R.id.btnSavePrefs)).perform(click());

        // 3. Charger
        onView(withId(R.id.btnLoadPrefs)).perform(click());

        // 4. Vérifier que le résultat contient le nom et la longueur du token
        onView(withId(R.id.tvResult))
                .check(matches(withText(containsString("name=" + testName))))
                .check(matches(withText(containsString("tokenLength=" + testToken.length()))));
    }
}