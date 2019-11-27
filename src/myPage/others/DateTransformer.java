package myPage.others;

public class DateTransformer {
    public static java.util.Date getJavaDate(java.sql.Date date){
        return new java.util.Date(date.getTime());
    }

    public static java.sql.Date getSqlDate(java.util.Date date){
        return new java.sql.Date(date.getTime());
    }
}
