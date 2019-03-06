import java.util.Scanner;

public class TimeClient {


    public static void main(String[] args) {
        Time time = new Time(04,10,0);
        //System.out.print(time.hour); //compiler error because hour is a private instance in the time class
       //System.out.println(time.getHour()); //this works because an accessor or gettor method is used to get time.
        Time.printTime(time);

        String s = time.toString();
        System.out.println(s);
        System.out.println(time);

        Time time1 = new Time(9, 30, 0.0);
        Time time2 = time1;
        Time time3 = new Time(9, 30, 0.0);

        time1.equals(time3);

        Time startTime = new Time(18,50,0.0);
        Time runningTime = new Time(2,16,0.0);

        Time endTime = startTime.add(runningTime);
        System.out.println(endTime);
        startTime.increment(120);




    }

}
