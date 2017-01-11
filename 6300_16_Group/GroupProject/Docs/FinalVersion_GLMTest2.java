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
public class GLMTest2 {
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
    * @author - yywxenia@gmail.com  //Yiwei Yan
    * Generated using Barista
    */
    @Test
    public void testGLMTest2() {
        mDevice.findObject(By.res(PACKAGE_NAME, "button2")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "editText")).setText("NewList1");
        mDevice.findObject(By.res(PACKAGE_NAME, "editText2")).setText("StoreA");
        mDevice.findObject(By.res(PACKAGE_NAME, "button10")).click();
        //TODO: Action to click on Adapter view element[ResourceID:R.id.typeList1] at position -1;
        //TODO: Action to click on Adapter view element[ResourceID:R.id.itemList] at position -1;
        mDevice.findObject(By.res(PACKAGE_NAME, "button11")).click();
        //TODO: Action to click on Adapter view element[ResourceID:R.id.list_test] at position -1;
        //TODO: Action to click on Adapter view element[ResourceID:R.id.list_test] at position -1;
        //TODO: Action to click on Adapter view element[ResourceID:R.id.list_test] at position -1;
        //TODO: Action to click on Adapter view element[ResourceID:R.id.list_test] at position -1;
        //TODO: Action to click on Adapter view element[ResourceID:R.id.list_test] at position -1;
        //TODO: Action to click on Adapter view element[ResourceID:R.id.list_test] at position -1;
        //TODO: Action to click on Adapter view element[ResourceID:R.id.list_test] at position -1;
        //TODO: Action to click on Adapter view element[ResourceID:R.id.list_test] at position -1;
        //TODO: Action to click on Adapter view element[ResourceID:R.id.listView1] at position -1;
        //TODO: Action to click on Adapter view element[ResourceID:R.id.listView1] at position -1;
        mDevice.findObject(By.res(PACKAGE_NAME, "clearAll")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "addItemG")).click();
        //TODO: Action to click on Adapter view element[ResourceID:R.id.itemlistview] at position -1;
        //TODO: Action to click on Adapter view element[ResourceID:R.id.itemlistview] at position -1;
        mDevice.findObject(By.res(PACKAGE_NAME, "button")).click();
        //TODO: Action to click on Adapter view element[ResourceID:R.id.itemlistview] at position -1;
        mDevice.findObject(By.res(PACKAGE_NAME, "button11")).click();
        //TODO: Action to click on view element[XPath:/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ListView/android.widget.RelativeLayout];
        mDevice.findObject(By.res(PACKAGE_NAME, "checkBox1")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "addItemG")).click();
        //TODO: Action to click on Adapter view element[ResourceID:R.id.itemlistview] at position -1;
        //TODO: Action to click on Adapter view element[ResourceID:R.id.itemlistview] at position -1;
        mDevice.findObject(By.res(PACKAGE_NAME, "button11")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "clearAll")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "save")).click();
        //TODO: Action to click on Adapter view element[ResourceID:R.id.list_test] at position -1;
        mDevice.findObject(By.res(PACKAGE_NAME, "button3")).click();
        //TODO: Action to click on Adapter view element[ResourceID:R.id.list_test] at position -1;
        mDevice.findObject(By.res(PACKAGE_NAME, "addItemG")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "button8")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "searchName")).setText("Green Pepper");
        mDevice.findObject(By.res(PACKAGE_NAME, "button12")).click();
        //TODO: Action to click on Adapter view element[ResourceID:R.id.searchitemlistview] at position -1;
        //TODO: Action to click on Adapter view element[ResourceID:R.id.itemlistview] at position -1;
        //TODO: Action to click on Adapter view element[ResourceID:R.id.itemlistview] at position -1;
        //TODO: Action to click on Adapter view element[ResourceID:R.id.itemlistview] at position -1;
        //TODO: Action to click on Adapter view element[ResourceID:R.id.itemlistview] at position -1;
        mDevice.findObject(By.res(PACKAGE_NAME, "button11")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "addItemG")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "button8")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "searchName")).setText("ItemA");
        mDevice.findObject(By.res(PACKAGE_NAME, "button12")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "button14")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "editText3")).setText("TestItemNew1");
        mDevice.findObject(By.res(PACKAGE_NAME, "editText4")).setText("NewType");
        mDevice.findObject(By.res(PACKAGE_NAME, "button9")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "searchName")).setText("Item");
        mDevice.findObject(By.res(PACKAGE_NAME, "button12")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "searchName")).setText("");
        mDevice.findObject(By.res(PACKAGE_NAME, "button12")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "button11")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "save")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "button4")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "editText3")).setText("TestItemNew2");
        mDevice.findObject(By.res(PACKAGE_NAME, "editText4")).setText("NewType");
        mDevice.findObject(By.res(PACKAGE_NAME, "button9")).click();
        //TODO: Action to click on view element[XPath:/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.ListView/android.widget.LinearLayout[2]];
        mDevice.findObject(By.res(PACKAGE_NAME, "addItemG")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "button10")).click();
        //TODO: Action to click on Adapter view element[ResourceID:R.id.typeList1] at position -1;
        //TODO: Action to click on Adapter view element[ResourceID:R.id.itemList] at position -1;
        mDevice.findObject(By.res(PACKAGE_NAME, "button11")).click();
        mDevice.findObject(By.res(PACKAGE_NAME, "save")).click();


    }
}