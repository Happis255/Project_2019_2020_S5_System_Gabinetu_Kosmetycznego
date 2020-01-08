package myPage.others;

import java.sql.Time;
import java.time.LocalTime;

public class TimeTransformer {
    public static LocalTime getJavaTime(java.sql.Time time){
        return time.toLocalTime();
    }

    public static java.sql.Time getSqlTime(LocalTime time){
        return Time.valueOf(time);
    }
}
