import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by orvillelim on 09/03/2017.
 */
public class main {

    static String  cssReplacement = "";  // }.d32_c
    static  String htmlReplacement = ""; // d32_c
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line = "";

            System.out.println("Enter HTML Replacement:"); // get string to be use on replacement
            htmlReplacement =  reader.readLine();

            System.out.println("Enter CSS Replacement:"); // get String to be use on css replacement
            cssReplacement =  reader.readLine();

            System.out.println("Enter HTML to parse:");  // get String to be parsed

            while ((line = reader.readLine()) !=  "</html>") {
                if (line.contains("class")){
                    line =  getClassNamesOnString(line);
                }
                if(line.contains("}.c")){
                    line = line.replaceAll("}.c", cssReplacement);
                }

                 System.out.println(line); //  print
            }

    }

    static  String getClassNamesOnString(String line) {
        Pattern p = Pattern.compile("class=\"*([^\"]*)\"");
        Matcher m = p.matcher(line);

        ArrayList<String> arrayList = new ArrayList<>();

        String[] lineX = line.split("class=\"*([^\"]*)\"");

        while (m.find()) {
            String classes = m.group(1);
            classes = classes.replaceAll("c", htmlReplacement);
            arrayList.add(classes);
        }

        String mod = "";

        for (int i = 0; i <= arrayList.size() -1 ; i++){
            String str = "";

            str = lineX[i] + "class=\""+arrayList.get(i) + "\"";
            mod += str;

                if (i >= arrayList.size() -1){
                    mod += lineX[i+1];
                    continue;
                }
        }

        line = mod;
        return line;

    }
}
