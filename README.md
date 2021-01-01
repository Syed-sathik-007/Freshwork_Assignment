# File based key-value data store that do basic create,read and delete operation using java.

# Steps to run:
1.The java code is platform independent. So, it can run in all kind of platform. 

2.Clone the code using the given code.

```
git clone https://github.com/Syed-sathik-007/Freshwork_Assignment.git
```

3. Create a new java class and import the lib folder in our repository.

4.Build the path to the jar files in the lib folder.

5.Import the library.
```
import Key_value_operation.CRD;
```
6. For referance, see the below code.
```
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
```

# Executed output

Your output may look like this.
```
Key added successfully
Key added successfully
Key is already present
Value of syed : 100
Value of vijay : 50
Key not found
Key deleted successfully
Key deleted successfully
No data Exists
```
