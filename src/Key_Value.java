import org.json.simple.JSONObject;
import java.io.*;
import Key_value_operation.CRD;
import java.util.*;


public class Key_Value {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        CRD.create("syed",100,3600);  // create(key,value,timelimit)   creating key with timelimit
        CRD.create("vijay",50);       // create(key,value)       creating key without timelimit
        CRD.create("syed",50);
        
        CRD.read("syed");               // read(key)    Displaye the value of the key
        CRD.read("vijay");
        CRD.read("freshworks");
        
        CRD.delete("syed");
        CRD.delete("vijay");
        CRD.delete("freshworks");
      }
}
