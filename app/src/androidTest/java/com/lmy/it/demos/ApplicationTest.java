package com.lmy.it.demos;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.lmy.it.bean.Food;
import com.lmy.it.database.FoodDatabaseAction;

import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void test() throws Exception {
        Food food = new Food();
        food.setName("肯德基");
        food.setPrice(60.00);
        FoodDatabaseAction foodDatabaseAction = FoodDatabaseAction.getDatabaseActionIntance(getContext());
        foodDatabaseAction.save(food);

    }

    public void test1() throws Exception {
        FoodDatabaseAction foodDatabaseAction = FoodDatabaseAction.getDatabaseActionIntance(getContext());
        List<Food> foods = foodDatabaseAction.query(null, null);

        for (Food f : foods ) {
            if (BuildConfig.DEBUG) Log.d("ApplicationTest", "f:" + f);
        }
    }

    public void test2() throws Exception {
        JniTest jniTest = new JniTest();
//        int result = jniTest.Mul(2, 4);
//        Log.d("test2()输出：", result + "");
    }
}