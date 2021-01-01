import java.io.IOException;
import java.util.Hashtable;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.time.LocalTime;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
public class test {


    public static void main(String args[]) throws IOException {
        Hashtable<String, JSONObject> KeyValuePair = new Hashtable<>();
       // JSONObject json = new JSONObject();
        JSONObject obj = new JSONObject();
        LocalTime now = LocalTime.now();
        System.out.println(now);
        obj.put("value", 10);
        obj.put("Time", now);


        //System.out.print(obj);
       // System.out.println(obj.num);
       /* Integer v = (Integer) obj.get("value");
        LocalTime t=(LocalTime) obj.get("Time");
        System.out.println(v);
        System.out.println(t);*/
        KeyValuePair.put("vijay",obj);
        System.out.println(KeyValuePair);
        FileOutputStream fos = new FileOutputStream("files.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(KeyValuePair);
        oos.close();
    }
}
