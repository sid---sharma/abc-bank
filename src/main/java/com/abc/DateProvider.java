package com.abc;

import java.util.Calendar;
import java.util.Date;

public class DateProvider {
    private static DateProvider instance = null;

    public static DateProvider getInstance() {
        if (instance == null)
            instance = new DateProvider();
        return instance;
    }

    public Date now() {
        return Calendar.getInstance().getTime();
    }
    
   public Date subtract15Days() {
	   Calendar cal = Calendar.getInstance();
	   cal.setTime(now());
	   cal.add(Calendar.DATE, -15);
	   Date dateBefore15Days = cal.getTime();
	   return dateBefore15Days;
   }
}
