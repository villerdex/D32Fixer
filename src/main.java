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

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String className = "";
        System.out.println("Enter String:");
        try {
            String line = "";
            while ((line = reader.readLine()) !=  "</html>") {

                if (line.contains("class")){
                    line =  getClassNamesOnString(line);
                }
                if(line.contains("}.c")){
                    line = line.replaceAll("}.c", "}.d32_c");
                }

                 System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static  String getClassNamesOnString(String line) {
        Pattern p = Pattern.compile("class=\"*([^\"]*)\"");
        Matcher m = p.matcher(line);

        ArrayList<String> arrayList = new ArrayList<>();


        String[] lineX = line.split("class=\"*([^\"]*)\"");

        while (m.find()) {
            String classes = m.group(1);
            classes = classes.replaceAll("c", "d32_c");
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
