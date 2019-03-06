public class Time {
    private int hour;
    private int minute;
    private double second;

    public Time () {
        this.hour = 0;
        this.minute = 0;
        this.second = 0.0;
    }

    public Time(int hour, int minute, double second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public int getHour(){
        return this.hour;
    }

    public int getMinute(){
        return this.minute;
    }

    public double getSecond() {
        return second;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setSecond(double second) {
        this.second = second;
    }

    //this is basically a toString method
    public static void printTime(Time t){
        System.out.printf("%02d:%02d:%04.1f\n", t.hour,t.minute,t.second);
    }

    public String toString(){
       return String.format("%02d:%02d:%04.1f\n", this.hour,this.minute,this.second);
    }

    public boolean equals(Time that) {
        return this.hour == that.hour
                && this.minute == that.minute
                && this.second == that.second;

    }
    //not a pure method
    public Time add(Time t2) {
        Time sum = new Time();
        sum.hour = this.hour + t2.hour;
        sum.minute = this.minute + t2.minute;
        sum.second = this.second + t2.second;

        if (sum.second >= 60) {
            sum.second -= 60;
            sum.minute += 1;
        }

        if (sum.minute >= 60) {
            sum.minute -= 60;
            sum.hour += 1;
        }

        return sum;
    }

    //pure method!
    public void increment(double seconds) {
        this.second += seconds;
        while (this.second >= 60.0){
            seconds -= 60.0;
            this.minute += 1;
        }
        while (this.minute >= 60){
            this.minute -= 60;
            this.hour += 1;
        }

    }

    public static void main(String[] args){

        Time time = new Time(11,50,59.9);



    }

}
