package com.amngostaran.customcalendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;

import com.amngostaran.customcalendar.costom.SimpleCalendar;


public class TestActivity extends AppCompatActivity {


  private GridView gridView;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test);

    SimpleCalendar simpleCalendar = (SimpleCalendar) findViewById(R.id.square_day);
    simpleCalendar.setUserCurrentInput(this,31, 4, 21);
//    simpleCalendar.setUserCurrentMonthYear(3,2017);

    simpleCalendar.setCallBack(new SimpleCalendar.DayClickListener() {
      @Override
      public void onDayClick(View view) {

      }
    });

  }
}
