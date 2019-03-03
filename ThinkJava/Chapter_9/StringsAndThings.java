public class StringsAndThings {

    public static void main(String[] args){

        String fruit = "banana";
        char letter = fruit.charAt(0);

        if (letter == 'a') {
            System.out.println('?');
        }


        System.out.println("Roman Alaphabet: ");
        for (char c = 'A'; c <= 'Z'; c++) {
            System.out.print(c);
        }
        System.out.println();

        System.out.println("Greek Alaphabet: ");
        for (int i = 913; i <= 937; i++){
            System.out.print((char) i);
        }

        String name = "Alan Turing";
        String uppername = name.toUpperCase();

        String text = "Computer Science is fun!";
        text = text.replace("Computer Science", "CS");

        for (int i = 0; i < fruit.length(); i++){
            char c = fruit.charAt(i);
            System.out.println(c);
        }

        for (char letter2: fruit.toCharArray()) {
            System.out.println(letter2);
        }

        int length = fruit.length();
        char last = fruit.charAt(length - 1);

        fruit.substring(0); //"banana"
        fruit.substring(2); //"nana"
        fruit.substring(6); //""
        fruit.substring(0,3); //"ban"
        fruit.substring(2,5);//"nan"
        //fruit.substring(i, i + length);

        String fruit2 = "banana";
        int index = fruit2.indexOf('a');//returns the index of the first time a is used
        int index2 = fruit2.indexOf('a',2);
        fruit.indexOf("nan");//from "banana" this returns 2

        String name1 = "Alan Turing";
        String name2 = "Ada Lovelace";
        if (name1.equals(name2)) {
            System.out.println("The names are the same.");
        }

        int diff = name1.compareTo(name2);
        if (diff == 0){
            System.out.println("The names are the same. ");
        } else if (diff < 0){
            System.out.println("name1 comes before name2");
        } else if (diff > 0){
            System.out.println("name2 comes before name1");
        }

        String str = "12345";
        int num = Integer.parseInt(str);

        int num2 = 12345;
        String str2 = Integer.toString(num2);

    }

    public static String reverse(String s) {
        String r = "";
        for (int i = s.length() - 1; i >= 0; i--){
            r = r + s.charAt(i);
        }

        return r;
    }

    public static String time(int hour, int minute){
        String ampm;
        if (hour < 12){
            ampm = "AM";
            if (hour == 0);
            hour = 12; //midnight
        } else {
            ampm = "PM";
            hour = hour - 12;
        }

        return String.format("%02d:%02d %s", hour,minute, ampm);
    }



}
