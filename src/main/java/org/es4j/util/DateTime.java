package org.es4j.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DateTime {
    private final Date time;

    public static DateTime utcNow() {
        return new DateTime();
    }

    public DateTime(int year, int month, int dayOfMonth,
                      int hourOfDay, int minute, int second, int millis) {
        Calendar cal = new GregorianCalendar();
        time = null;
    }

    public DateTime(long i) {
        time = new Date(i);
    }

    public DateTime() {
        time = new Date();
    }
    
    public DateTime(int year, int month, int day) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean after(DateTime when) {
        return time.after(when.getTime());

    }

    public boolean before(DateTime when) {
        return time.before(when.getTime());
    }

    public long getMillis() {
        return time.getTime();
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    public DateTime toUniversalTime() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private Date getTime() {
        return time;
    }

    public boolean isBefore(DateTime epochTime) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Object getTicks() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
