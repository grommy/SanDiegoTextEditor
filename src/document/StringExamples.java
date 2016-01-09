package document;

import java.util.List;

/**
 * Created by admin on 1/9/16.
 */
public class StringExamples {
    public static void main(String[] args) {
        String s = "%one%%two%%%three%%%%";
        String[] splitedString;

//        splitedString = s.split("%+");
        splitedString = s.split("[a-z]+");
//        splitedString = s.split("one|two|three");
//        splitedString = s.split("[one,two,three]");
        for (String string: splitedString) {
            System.out.println(string);
        }

        BasicDocument doc = new BasicDocument("one (1), two (2), three (3)");
        List<String> split;

//        split = doc.getTokens("[^, ]+");
//        split = doc.getTokens("[a-z()0-9]+");
//        split = doc.getTokens("[a-z]+|[()0-9]+");
        split = doc.getTokens("[^,]+");
        System.out.println(split);

        String s1 = new String("String 1");
        String s2 = "String 1";
        if (s1 == s2) {
            System.out.println("Equal");
        }
            else{
                System.out.println("Not equal");
            }


        String text = "My ";
        s2 = "String";
        text = text + s2;
        System.out.println(text);

        text = "My ";
        text.concat("String");
        System.out.println(text);

        s1 = "My String";
        text = s1;

        System.out.println(text);
    }
}
