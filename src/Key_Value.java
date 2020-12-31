import org.json.simple.JSONObject;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.*;


public class Key_Value {

    public static void create(String key, int value, int sec) throws IOException {
        LocalTime now = LocalTime.now();
        now=now.plusSeconds(sec);                   //Time to live
        JSONObject json = new JSONObject();         //json to store the value
        json.put("value",value);
        json.put("timelimit",now);
        KeyValuePair.put(key, json);                // Create the key and value as json object and store in hashtable
        FileOutputStream fos = new FileOutputStream("data\\files.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(KeyValuePair);
        oos.close();
    }

    public static void read(String keyname){
        if(KeyValuePair.containsKey(keyname)){                      //Check the key is already present or not
            JSONObject json = KeyValuePair.get(keyname);
            Integer value = (Integer) json.get("value");
            LocalTime timelimit =(LocalTime) json.get("timelimit");

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


    public static void delete(String keyname) throws IOException {
        if(KeyValuePair.containsKey(keyname)) {                                   //Check the key is already present or not
            JSONObject json = KeyValuePair.get(keyname);
            LocalTime timelimit = (LocalTime) json.get("timelimit");

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

    static Hashtable<String, JSONObject> KeyValuePair = new Hashtable<>();      //Using Hashtable because it is thread safe

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Scanner scan = new Scanner(System.in);
        final int filesize = 1024*1024*1024;    //File size must be less than 1GB

        while(true){

            System.out.println("Enter the Option that you want to perform:");
            System.out.println("1. Create");
            System.out.println("2. Read");
            System.out.println("3. Delete");
            System.out.println("4. Exit");

            System.out.println("Enter your option");
            int n=scan.nextInt();

            switch(n){

                case 1:
                    File f = new File("data\\files.txt");
                    if (f.exists()) {                                           // check whether the file exists..if not create a new file
                        long bytes = Files.size(Paths.get("data\\files.txt"));
                        if(bytes>filesize) {
                            System.out.println("File size limit exceed");        // check the file size is less than the limit
                            break;
                        }
                        else{
                            FileInputStream fis = new FileInputStream("data\\files.txt");
                            ObjectInputStream ois = new ObjectInputStream(fis);
                            KeyValuePair = (Hashtable<String, JSONObject>) ois.readObject();
                            ois.close();
                            System.out.println("Enter the key:");
                            String key = scan.next();
                            key = key.substring(0, Math.min(key.length(), 32));         //Key is capped at 32chars
                            if(KeyValuePair.containsKey(key)){                      //Check the key is already present or not
                                System.out.println("Key is already present");
                                break;
                            }
                            else{
                                System.out.println("Enter the value:");
                                int value = scan.nextInt();
                                System.out.println("Enter the timeLimit in second to expires");
                                int sec= scan.nextInt();
                                create(key,value,sec);
                                System.out.println("Key added successfully");
                            }

                        }
                    }
                    else {
                        System.out.println("Enter the key:");
                        String key = scan.next();
                        System.out.println("Enter the value:");
                        int value = scan.nextInt();
                        System.out.println("Enter the timeLimit in second to expires");
                        int sec= scan.nextInt();
                        create(key,value,sec);
                        System.out.println("Key added successfully");
                    }
                    break;

                case 2:
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
                            System.out.println("Enter the Key to read the value");
                            String keyname = scan.next();                                 //Case to read the key and value
                            read(keyname);
                        }
                    }
                    else{
                        System.out.println("No data Exists");
                    }
                    break;

                case 3:
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
                            System.out.println("Enter the key that to be deleted");
                            String keyname = scan.next();
                            delete(keyname);                                               //Case to delete the key and value
                        }
                    }
                    else{
                        System.out.println("No data Exists");
                    }
                    break;
                case 4:
                    System.exit(1);
            }

        }
    }
}
