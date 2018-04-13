package com.amngostaran.customcalendar.costom;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.amngostaran.customcalendar.R;

import java.util.Calendar;


public class SimpleCalendar extends LinearLayout {

  private static final String CUSTOM_GREY = "#a0a0a0";


  private Button selectedDayButton;
  private Button[] days;
  private Button[] unActiveBottons;
  LinearLayout weekOneLayout;
  LinearLayout weekTwoLayout;
  LinearLayout weekThreeLayout;
  LinearLayout weekFourLayout;
  LinearLayout weekFiveLayout;
  LinearLayout weekSixLayout;
  private LinearLayout[] weeks;

//  private int countInactivesDay, daysInCurrentMonth, firstDayOfCurrentMonth;
  //  int userMonth, userYear;
  private DayClickListener mListener;
  private Drawable userDrawable;

  private Calendar calendar;
  LayoutParams defaultButtonParams;
  private LayoutParams userButtonParams;

  public SimpleCalendar(Context context) {
    super(context);
    init(context);
  }

  public SimpleCalendar(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public SimpleCalendar(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }


  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public SimpleCalendar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init(context);
  }

  private void init(Context context) {

    DisplayMetrics metrics = getResources().getDisplayMetrics();

    View view = LayoutInflater.from(context).inflate(R.layout.simple_calendar, this, true);
    calendar = Calendar.getInstance();

    weekOneLayout = (LinearLayout) view.findViewById(R.id.calendar_week_1);
    weekTwoLayout = (LinearLayout) view.findViewById(R.id.calendar_week_2);
    weekThreeLayout = (LinearLayout) view.findViewById(R.id.calendar_week_3);
    weekFourLayout = (LinearLayout) view.findViewById(R.id.calendar_week_4);
    weekFiveLayout = (LinearLayout) view.findViewById(R.id.calendar_week_5);
    weekSixLayout = (LinearLayout) view.findViewById(R.id.calendar_week_6);


    initializeDaysWeeks();
    if (userButtonParams != null) {
      defaultButtonParams = userButtonParams;
    } else {
      defaultButtonParams = getdaysLayoutParams();
    }
    addDaysinCalendar(defaultButtonParams, context, metrics);
    addDaysinCalendarA(defaultButtonParams, context, metrics);
//    initCalendarWithDate(context, daysInCurrentMonth, firstDayOfCurrentMonth, countInactivesDay);

  }

  private void initializeDaysWeeks() {
    weeks = new LinearLayout[6];
    days = new Button[6 * 7];

    unActiveBottons = new Button[6 * 7];

    weeks[0] = weekOneLayout;
    weeks[1] = weekTwoLayout;
    weeks[2] = weekThreeLayout;
    weeks[3] = weekFourLayout;
    weeks[4] = weekFiveLayout;
    weeks[5] = weekSixLayout;


  }

  private void initCalendarWithDate(final Context context, int daysInCurrentMonth, int firstDayOfCurrentMonth, int countUnactivesDay) {


    int dayNumber = 1;

    countUnactivesDay += firstDayOfCurrentMonth;
    for (int i = firstDayOfCurrentMonth; i < firstDayOfCurrentMonth + daysInCurrentMonth; ++i) {

      int[] dateArr = new int[3];
      dateArr[0] = dayNumber;
      days[i].setTag(dateArr);
      days[i].setText(String.valueOf(dayNumber));

      if (i < countUnactivesDay) {
        days[i].setTextColor(Color.LTGRAY); // #d3d3d3
        unActiveBottons[i].setText(String.valueOf(dayNumber));
      } else {
        days[i].setTextColor(Color.BLACK);
        days[i].setBackgroundColor(Color.TRANSPARENT);
      }


      final int finalI = i;
      days[i].setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          onDayClick(v, finalI, context);
        }
      });
      ++dayNumber;
    }


  }

  @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
  public void onDayClick(View view, int j, Context context) {
    mListener.onDayClick(view);

    if (unActiveBottons[j].getText().toString().equals("")) {

      if (selectedDayButton != null) {

        selectedDayButton.setBackground(context.getResources().getDrawable(R.drawable.circle_shape_unselect));
        if (selectedDayButton.getCurrentTextColor() != Color.RED) {
          selectedDayButton.setTextColor(Color.BLACK);
        }


      }

      selectedDayButton = (Button) view;
      if (selectedDayButton.getTag() != null) {
        int[] dateArray = (int[]) selectedDayButton.getTag();
//        pickedDateDay = dateArray[0];
      }


      selectedDayButton.setBackground(context.getResources().getDrawable(R.drawable.circle_shape));

      if (selectedDayButton.getCurrentTextColor() != Color.RED) {
        selectedDayButton.setTextColor(Color.WHITE);
      }


    }

  }

  private void addDaysinCalendar(LayoutParams buttonParams, Context context, DisplayMetrics metrics) {
    int engDaysArrayCounter = 0;

    for (int weekNumber = 0; weekNumber < 6; ++weekNumber) {
      for (int dayInWeek = 0; dayInWeek < 7; ++dayInWeek) {
        final Button day = new Button(context);
        day.setTextColor(Color.parseColor(CUSTOM_GREY));
        day.setBackgroundColor(Color.TRANSPARENT);
        day.setLayoutParams(buttonParams);
        day.setTextSize((int) metrics.density * 8);
        day.setSingleLine();

        days[engDaysArrayCounter] = day;
        weeks[weekNumber].addView(day);

        ++engDaysArrayCounter;
      }
    }
  }

  private void addDaysinCalendarA(LayoutParams buttonParams, Context context, DisplayMetrics metrics) {
    int engDaysArrayCounter = 0;


    for (int weekNumber = 0; weekNumber < 6; ++weekNumber) {
      for (int dayInWeek = 0; dayInWeek < 7; ++dayInWeek) {
        final Button day = new Button(context);
        day.setTextColor(Color.parseColor(CUSTOM_GREY));
        day.setBackgroundColor(Color.TRANSPARENT);
        day.setLayoutParams(buttonParams);
        day.setTextSize((int) metrics.density * 8);
        day.setSingleLine();

        unActiveBottons[engDaysArrayCounter] = day;

        ++engDaysArrayCounter;
      }
    }
  }

  private LayoutParams getdaysLayoutParams() {
    LayoutParams buttonParams = new LayoutParams(
      ViewGroup.LayoutParams.MATCH_PARENT,
      ViewGroup.LayoutParams.MATCH_PARENT);
    buttonParams.weight = 1;
    return buttonParams;
  }

  public void setUserDaysLayoutParams(LayoutParams userButtonParams) {
    this.userButtonParams = userButtonParams;
  }

  public void setUserCurrentInput(Context context, int daysInCurrentMonth, int firstDayOfCurrentMonth, int countInactivesDay) {
    initCalendarWithDate(context, daysInCurrentMonth, firstDayOfCurrentMonth, countInactivesDay);
  }


  public void setDayBackground(Drawable userDrawable) {
    this.userDrawable = userDrawable;
  }

  public interface DayClickListener {
    void onDayClick(View view);
  }

  public void setCallBack(DayClickListener mListener) {
    this.mListener = mListener;
  }

}
