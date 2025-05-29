import java.util.Calendar;
import java.util.GregorianCalendar;

public class TodaysDate {
    //member variables
    String time;
    public int day;
    private int month;
    protected int year;

    //constructor para uso normal
    public TodaysDate () {
        //no es necesario pasar nada
    }

    //constructor para testing
    private Calendar testCalendar; //atributo para guardar calendario de testeo

    public TodaysDate(Calendar calendarForTesting) {
        this.testCalendar = calendarForTesting;
    }

    //methods
    public void printDateAndTime(){
        GregorianCalendar calendar = new GregorianCalendar();
        time = calendar.get(Calendar.HOUR_OF_DAY) + ":"
                + calendar.get(Calendar.MINUTE) + ":"
                + calendar.get(Calendar.SECOND);
        day = calendar.get(Calendar.DATE);
        month = calendar.get(Calendar.MONTH) + 1;
        year = calendar.get(Calendar.YEAR);

        System.out.println("Time: " + time);
        System.out.println("Date: " + month + " " + day + " " + year);
    }

    //métodos para acceder a los atributos privados desde el test
    public String getTime() {
        return time;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}
