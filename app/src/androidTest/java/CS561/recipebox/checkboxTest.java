package CS561.recipebox;

import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.CoordinatesProvider;
import androidx.test.espresso.action.GeneralClickAction;
import androidx.test.espresso.action.Press;
import androidx.test.espresso.action.Tap;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;


//Uses testClass as a template to run tests to confirm that query system is correctly implemented
@MediumTest
@RunWith(AndroidJUnit4.class)
public class checkboxTest
{

    private float[] lastTouchDownXY = new float[2];
    int main()
    {




        Log.d("Test", "Test");
        try {
            testSearchBar();
        }
        catch (Exception e)
        {

        }

        return 0;
    }

    @Rule
    public ActivityTestRule<MainActivity> rule  = new  ActivityTestRule<>(MainActivity.class);

    @Test
    public void testSearchBar() throws Exception
    {
        Log.d("Test", "Test executed.");
        MainActivity activity = rule.getActivity();
        View searchView = activity.findViewById(R.id.searchView);
        assertThat(searchView,notNullValue());
        assertThat(searchView, instanceOf(SearchView.class));
        Random rand = new Random();

        List<String> testInput = new ArrayList<String>();
        testInput.add("Fri");
        testInput.add("frie");
        testInput.add("fR");
        testInput.add("FR");
        testInput.add("");
        testInput.add("sTE");
        testInput.add("ST");
        testInput.add("Ste");
        testInput.add("S");
        testInput.add("slide");
        testInput.add("slider");
        testInput.add("Sli");
        testInput.add("s");


        String randInput;
        for (int i = 0; i < 10; i++)
        {
            randInput = "";
            int n = rand.nextInt(10) + 1;
            for (int j = 0; j < n; j++)
            {
                randInput += (char)(rand.nextInt(26) + 'a');
            }
            testInput.add(randInput);
        }


        for (int i = 0; i < testInput.size(); i++)
        {


            Espresso.onView(withId(R.id.searchView)).perform(clickPosition(activity,20,20));



            if (rand.nextBoolean())
                Espresso.onView(withId(R.id.radio_title)).perform(click());
            else
                Espresso.onView(withId(R.id.radio_ingredient)).perform(click());


            Espresso.onView(withId(R.id.searchView)).perform(typeText(testInput.get(i) + "\n"));


            Espresso.onView(withId(R.id.searchView)).perform(clickPosition(activity, 20, 20));
            for (int j = 0; j < testInput.size(); j++) {
                Espresso.onView(withId(R.id.searchView))
                        .perform(pressKey(KeyEvent.KEYCODE_DEL));
            }

            Log.d("Unit Test input", testInput.get(i));

        }
    }

    public static ViewAction clickPosition (MainActivity activity, int x, int y)
    {
        CoordinatesProvider coordProvider = new CoordinatesProvider() {
            @Override
            public float[] calculateCoordinates(View view)
            {
                return coords(activity, x, y);
            }
        };
        return new GeneralClickAction(Tap.SINGLE, coordProvider, Press.FINGER);
    }

    public static float[] coords(MainActivity activity, int x, int y)
    {
        int[] screenCoords = new int[2];
        View view = activity.findViewById(R.id.searchView);
        view.getLocationOnScreen(screenCoords);
        float[] coordinates = {screenCoords[0] + x, screenCoords[1] + y};
        return coordinates;
    }
}

