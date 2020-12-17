import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class convertFiletoString {

    private String content;
    private FileReader fr;

    convertFiletoString(String file)
    {
        try {
            fr = new FileReader(file);


            //     https://www.journaldev.com/875/java-read-file-to-string#:~:text=
            //     Java%20read%20file%20to%20String%20using%20BufferedReader,-We%20
            //     can%20use&text=BufferedReader%20reader%20%3D%20new%20BufferedReader(new,readLine())%20!%3D


            BufferedReader reader = new BufferedReader(fr);
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            String ls = System.getProperty("line.separator");
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            reader.close();
            content = stringBuilder.toString();
        }
        catch(Exception e)
        {

        }
    }


    public String getStringOutput()
    {
        return content;
    }
}