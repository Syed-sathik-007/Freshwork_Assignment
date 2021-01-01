package Key_value_operation;

import org.json.simple.JSONObject;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.Hashtable;

public class CRD {

    static Hashtable<String, JSONObject> KeyValuePair = new Hashtable<>();

    public static void create(String key, int value, int sec) throws IOException, ClassNotFoundException {
        final int filesize = 1024*1024*1024;    //File size must be less than 1GB
        File f = new File("data\\files.txt");
        if (f.exists()) {                                           // check whether the file exists..if not create a new file
            long bytes = Files.size(Paths.get("data\\files.txt"));
            if(bytes>filesize) {
                System.out.println("File size limit exceed");        // check the file size is less than the limit
            }
            else{
                FileInputStream fis = new FileInputStream("data\\files.txt");
                ObjectInputStream ois = new ObjectInputStream(fis);
                KeyValuePair = (Hashtable<String, JSONObject>) ois.readObject();
                ois.close();
                key = key.substring(0, Math.min(key.length(), 32));         //Key is capped at 32chars
                if(KeyValuePair.containsKey(key)){                      //Check the key is already present or not
                    System.out.println("Key is already present");
                }
                else{
                    LocalTime now = LocalTime.now();
                    now=now.plusSeconds(sec);                   //Time to live
                    JSONObject json = new JSONObject();         //json to store the value
                    json.put("value",value);
                    json.put("timelimit",now);
                    json.put("lifelong",false);
                    KeyValuePair.put(key, json);                // Create the key and value as json object and store in hashtable
                    FileOutputStream fos = new FileOutputStream("data\\files.txt");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(KeyValuePair);
                    oos.close();
                    System.out.println("Key added successfully");
                }

            }
        }
        else {
            LocalTime now = LocalTime.now();
            now=now.plusSeconds(sec);                   //Time to live
            JSONObject json = new JSONObject();         //json to store the value
            json.put("value",value);
            json.put("timelimit",now);
            json.put("lifelong",false);
            KeyValuePair.put(key, json);                // Create the key and value as json object and store in hashtable
            FileOutputStream fos = new FileOutputStream("data\\files.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(KeyValuePair);
            oos.close();
            System.out.println("Key added successfully");
        }
    }


    public static void create(String key, int value) throws IOException, ClassNotFoundException {
        int sec=0;
        final int filesize = 1024*1024*1024;    //File size must be less than 1GB
        File f = new File("data\\files.txt");
        if (f.exists()) {                                           // check whether the file exists..if not create a new file
            long bytes = Files.size(Paths.get("data\\files.txt"));
            if(bytes>filesize) {
                System.out.println("File size limit exceed");        // check the file size is less than the limit
            }
            else{
                FileInputStream fis = new FileInputStream("data\\files.txt");
                ObjectInputStream ois = new ObjectInputStream(fis);
                KeyValuePair = (Hashtable<String, JSONObject>) ois.readObject();
                ois.close();
                key = key.substring(0, Math.min(key.length(), 32));         //Key is capped at 32chars
                if(KeyValuePair.containsKey(key)){                      //Check the key is already present or not
                    System.out.println("Key is already present");
                }
                else{
                    LocalTime now = LocalTime.now();
                    now=now.plusSeconds(sec);                   //Time to live
                    JSONObject json = new JSONObject();         //json to store the value
                    json.put("value",value);
                    json.put("timelimit",now);
                    json.put("lifelong",true);
                    KeyValuePair.put(key, json);                // Create the key and value as json object and store in hashtable
                    FileOutputStream fos = new FileOutputStream("data\\files.txt");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(KeyValuePair);
                    oos.close();
                    System.out.println("Key added successfully");
                }

            }
        }
        else {
            LocalTime now = LocalTime.now();
            now=now.plusSeconds(sec);                   //Time to live
            JSONObject json = new JSONObject();         //json to store the value
            json.put("value",value);
            json.put("timelimit",now);
            json.put("lifelong",true);
            KeyValuePair.put(key, json);                // Create the key and value as json object and store in hashtable
            FileOutputStream fos = new FileOutputStream("data\\files.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(KeyValuePair);
            oos.close();
            System.out.println("Key added successfully");
        }
    }

    public static void read(String keyname) throws IOException, ClassNotFoundException {
        File fn = new File("data\\files.txt");
        if(fn.exists()){
            FileInputStream fis = new FileInputStream("data\\files.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            KeyValuePair = (Hashtable<String, JSONObject>) ois.readObject();
            ois.close();
            if(KeyValuePair.isEmpty()){
                System.out.println("No data Exists");
            }
            else {
                if(KeyValuePair.containsKey(keyname)){                      //Check the key is already present or not
                    JSONObject json = KeyValuePair.get(keyname);
                    Integer value = (Integer) json.get("value");
                    LocalTime timelimit =(LocalTime) json.get("timelimit");
                    boolean b=(boolean)json.get("lifelong");
                    if(b){
                        System.out.println("Value of "+ keyname +" : " +value);
                        return;
                    }
                    int t=LocalTime.now().compareTo(timelimit);
                    if(t<0){                                                //check whether the key is alive
                        System.out.println("Value of "+ keyname +" : " +value);
                    }
                    else{
                        System.out.println("The time limit has exceed for that key");
                    }
                }
                else{
                    System.out.println("Key not found");
                }
            }
        }
        else{
            System.out.println("No data Exists");
        }
    }

    public static void delete(String keyname) throws IOException, ClassNotFoundException {
        File fd = new File("data\\files.txt");
        if(fd.exists()){
            FileInputStream fis = new FileInputStream("data\\files.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            KeyValuePair = (Hashtable<String, JSONObject>) ois.readObject();
            ois.close();
            if(KeyValuePair.isEmpty()){
                System.out.println("No data Exists");
            }
            else {
                if(KeyValuePair.containsKey(keyname)) {                                   //Check the key is already present or not
                    JSONObject json = KeyValuePair.get(keyname);
                    LocalTime timelimit = (LocalTime) json.get("timelimit");
                    boolean b=(boolean)json.get("lifelong");
                    if(b){
                        KeyValuePair.remove(keyname);
                        System.out.println("Key deleted successfully");
                        FileOutputStream fos = new FileOutputStream("data\\files.txt");
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(KeyValuePair);
                        oos.close();
                        return;
                    }
                    int t = LocalTime.now().compareTo(timelimit);

                    if (t < 0) {                                //Delete the Key if system time is less than Time-To-Live property
                        KeyValuePair.remove(keyname);
                        System.out.println("Key deleted successfully");
                        FileOutputStream fos = new FileOutputStream("data\\files.txt");
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(KeyValuePair);
                        oos.close();
                    } else {
                        System.out.println("The time limit has exceed");
                    }
                }
                else{
                    System.out.println("Key not found");
                }
            }
        }
        else{
            System.out.println("No data Exists");
        }
    }
}
