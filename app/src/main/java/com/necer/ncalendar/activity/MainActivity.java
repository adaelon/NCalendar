package com.necer.ncalendar.activity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import com.necer.enumeration.CheckModel;
import com.necer.ncalendar.R;
import com.necer.ncalendar.TestActivity;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvVersion = (TextView) findViewById(R.id.tv_version);
        tvVersion.setText("版本：" + Utils.getCurrentVersion(this));
        createNotificationChannel();
        scheduleAlarm();



    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Daily Notifications";
            String description = "Channel for daily alarm notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("daily_alarm_channel", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void scheduleAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        Calendar calendar = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            calendar = Calendar.getInstance();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            calendar.setTimeInMillis(System.currentTimeMillis());
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            calendar.set(Calendar.HOUR_OF_DAY, 22);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            calendar.set(Calendar.MINUTE, 15);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, alarmIntent);
        }
    }

    public void month_selected(View view) {
        startActivity(getNewIntent(TestMonthActivity.class, CheckModel.SINGLE_DEFAULT_CHECKED, "月日历默认选中"));
    }

    public void month_unSelected(View view) {
        startActivity(getNewIntent(TestMonthActivity.class, CheckModel.SINGLE_DEFAULT_UNCHECKED, "月日历默认不选中"));
    }

    public void month_multiple(View view) {
        startActivity(getNewIntent(TestMonthActivity.class, CheckModel.MULTIPLE, "月日历多选"));
    }

    public void week_selected(View view) {
        startActivity(getNewIntent(TestWeekActivity.class, CheckModel.SINGLE_DEFAULT_CHECKED, "周日历默认选中"));
    }

    public void week_unSelected(View view) {
        startActivity(getNewIntent(TestWeekActivity.class, CheckModel.SINGLE_DEFAULT_UNCHECKED, "周日历默认不选中"));
    }

    public void week_multiple(View view) {
        startActivity(getNewIntent(TestWeekActivity.class, CheckModel.MULTIPLE, "周日历多选"));
    }

    public void miui9_selected(View view) {
        startActivity(getNewIntent(TestMiui9Activity.class, CheckModel.SINGLE_DEFAULT_CHECKED, "miui9默认选中"));
    }

    public void miui9_unSelected(View view) {
        startActivity(getNewIntent(TestMiui9Activity.class, CheckModel.SINGLE_DEFAULT_UNCHECKED, "miui9默认不选中"));
    }

    public void miui9_multiple(View view) {
        startActivity(getNewIntent(TestMiui9Activity.class, CheckModel.MULTIPLE, "miui9多选"));
    }

    public void miui10_selected(View view) {
        startActivity(getNewIntent(TestMiui10Activity.class, CheckModel.SINGLE_DEFAULT_CHECKED, "miui10默认选中"));
    }

    public void miui10_unSelected(View view) {
        startActivity(getNewIntent(TestMiui10Activity.class, CheckModel.SINGLE_DEFAULT_UNCHECKED, "miui10默认不选中"));
    }

    public void miui10_multiple(View view) {
        startActivity(getNewIntent(TestMiui10Activity.class, CheckModel.MULTIPLE, "miui10多选"));
    }

    public void emiui_selected(View view) {
        startActivity(getNewIntent(TestEmuiActivity.class, CheckModel.SINGLE_DEFAULT_CHECKED, "emiui默认选中"));
    }

    public void emiui_unSelected(View view) {
        startActivity(getNewIntent(TestEmuiActivity.class, CheckModel.SINGLE_DEFAULT_UNCHECKED, "emiui默认不选中"));
    }

    public void emiui_multiple(View view) {
        startActivity(getNewIntent(TestEmuiActivity.class, CheckModel.MULTIPLE, "emiui多选"));
    }

    public void toHoldWeek(View view) {
        startActivity(new Intent(this, TestWeekHoldActivity.class));
    }

    public void addView(View view) {
        startActivity(new Intent(this, TestAddViewActivity.class));
    }

    public void custom_painter(View view) {
        startActivity(new Intent(this, CustomCalendarActivity.class));
    }

    public void viewpager(View view) {
        startActivity(new Intent(this, TestViewPagerActivity.class));
    }

    public void general(View view) {
        startActivity(new Intent(this, TestGeneralActivity.class));
    }

    public void stretch(View view) {
        startActivity(new Intent(this, TestStretchActivity.class));
    }

    public void test(View view) {
        startActivity(new Intent(this, TestActivity.class));
    }

    public void testAdapter(View view) {
        startActivity(new Intent(this, TestAdapterActivity.class));
    }


    private Intent getNewIntent(Class<? extends BaseActivity> clazz, CheckModel checkModel, String title) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra("selectedModel", checkModel);
        intent.putExtra("title", title);
        return intent;
    }



}