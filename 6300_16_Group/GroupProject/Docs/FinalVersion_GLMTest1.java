package edu.gatech.seclass.glm;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class GLMTesting {
    private static final String PACKAGE_NAME = "edu.gatech.seclass.glm";
    private static final int LAUNCH_TIMEOUT = 5000;
    private UiDevice mDevice;

    @Before
    public void startMainActivityFromHomeScreen() {
        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // Start from the home screen
        mDevice.pressHome();

        // Wait for launcher
        final String launcherPackage = mDevice.getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                LAUNCH_TIMEOUT);

        // Launch the app
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(PACKAGE_NAME);
        // Clear out any previous instances
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        // Wait for the app to appear
        mDevice.wait(Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)),
                LAUNCH_TIMEOUT);
    }

    @Test
    public void checkPreconditions() {
        assertThat(mDevice, notNullValue());
    }

    /**
    * Test for GLM
    * @author - yywxenia@gmail.com    //Yiwei Yan
    * Generated using Barista 
    */
    @Test
    public void testGLMTesting() {
        
        mDevice.findObject(By.res(PACKAGE_NAME, "button2")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "button2")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "editText")).setText("List3");
        mDevice.findObject(By.res(PACKAGE_NAME, "editText2")).setText("Walgreens");
        mDevice.findObject(By.res(PACKAGE_NAME, "button10")).click();
        //TODO: Action to click on Adapter view element[ResourceID:R.id.typeList1] at position -1;
        //TODO: Action to click on Adapter view element[ResourceID:R.id.typeList1] at position -1;
        //TODO: Action to click on Adapter view element[ResourceID:R.id.itemlistview] at position -1;
        mDevice.findObject(By.res(PACKAGE_NAME, "button11")).click();
        //TODO: Action to click on Adapter view element[ResourceID:R.id.list_test] at position -1;
        
        mDevice.findObject(By.res(PACKAGE_NAME, "addItemG")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "button10")).click();
        //TODO: Action to click on Adapter view element[ResourceID:R.id.typeList1] at position -1;
        //TODO: Action to click on Adapter view element[ResourceID:R.id.typeList1] at position -1;
        //TODO: Action to click on Adapter view element[ResourceID:R.id.itemlistview] at position -1;
        mDevice.findObject(By.res(PACKAGE_NAME, "button8")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "searchName")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "searchName")).setText("pet");
        mDevice.findObject(By.res(PACKAGE_NAME, "button12")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "searchName")).setText("chicken");
        mDevice.findObject(By.res(PACKAGE_NAME, "button12")).click();
        //TODO: Action to click on Adapter view element[ResourceID:R.id.searchitemlistview] at position -1;
        //TODO: Action to click on Adapter view element[ResourceID:R.id.itemlistview] at position -1;
        mDevice.findObject(By.res(PACKAGE_NAME, "button")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "button11")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "save")).click();
        //TODO: Action to click on Adapter view element[ResourceID:R.id.list_test] at position -1;
        mDevice.findObject(By.res(PACKAGE_NAME, "addItemG")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "sub1")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "button11")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "addItemG")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "button8")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "searchName")).setText("cat food");
        mDevice.findObject(By.res(PACKAGE_NAME, "button12")).click();
        //TODO: Action to click on Adapter view element[ResourceID:R.id.searchitemlistview] at position -1;
        
        mDevice.findObject(By.res(PACKAGE_NAME, "button8")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "editText3")).setText("test");
        mDevice.findObject(By.res(PACKAGE_NAME, "editText4")).setText("type");
        mDevice.findObject(By.res(PACKAGE_NAME, "editText3")).setText("test1");
        mDevice.findObject(By.res(PACKAGE_NAME, "editText4")).setText("type1");
        mDevice.findObject(By.res(PACKAGE_NAME, "button9")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "searchName")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "searchName")).setText("test1");
        mDevice.findObject(By.res(PACKAGE_NAME, "button12")).click();
        //TODO: Action to click on Adapter view element[ResourceID:R.id.searchitemlistview] at position -1;
        //TODO: Action to click on Adapter view element[ResourceID:R.id.itemlistview] at position -1;
        mDevice.findObject(By.res(PACKAGE_NAME, "button11")).click();
        //TODO: Action to click on Adapter view element[ResourceID:R.id.listView1] at position -1;
        //TODO: Action to click on Adapter view element[ResourceID:R.id.listView1] at position -1;
        //TODO: Action to click on Adapter view element[ResourceID:R.id.listView1] at position -1;
        
        mDevice.findObject(By.res(PACKAGE_NAME, "clearAll")).click();
        //TODO: Action to click on view element[XPath:/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.ListView/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.CheckBox];
        mDevice.findObject(By.res(PACKAGE_NAME, "button3")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "button4")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "editText3")).setText("test2");
        mDevice.findObject(By.res(PACKAGE_NAME, "editText4")).setText("type");
        mDevice.findObject(By.res(PACKAGE_NAME, "button9")).click();
        //TODO: Action to click on view element[XPath:/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.ListView/android.widget.LinearLayout[1]];
        mDevice.findObject(By.res(PACKAGE_NAME, "addItemG")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "searchName")).setText("test3");
        mDevice.findObject(By.res(PACKAGE_NAME, "button12")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "button12")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "searchName")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "searchName")).setText("test2");
        mDevice.findObject(By.res(PACKAGE_NAME, "button12")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "searchName")).setText("test3");
        mDevice.findObject(By.res(PACKAGE_NAME, "button12")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "searchName")).setText("test2");
        //TODO: Action to click on view element[XPath:/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout];
        mDevice.findObject(By.res(PACKAGE_NAME, "button11")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "save")).click();


    }
}