import org.json.simple.JSONObject;
import java.io.*;
import Key_value_operation.CRD;
import java.util.*;


public class Key_Value {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Scanner scan = new Scanner(System.in);
        String key;
        int value,sec;
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
                    System.out.println("Enter the key:");
                    key = scan.next();
                    System.out.println("Enter the value:");
                    value = scan.nextInt();
                    System.out.println("Do you need timelimit( 1:YES 0:NO)");
                    int k = scan.nextInt();
                    if (k==1) {
                        System.out.println("Enter the timeLimit in second to expires");
                        sec= scan.nextInt();
                        CRD.create(key,value,sec);
                    }
                    CRD.create(key,value);
                    break;

                case 2:
                    System.out.println("Enter the key:");
                    key = scan.next();
                    CRD.read(key);
                    break;

                case 3:
                    System.out.println("Enter the key:");
                    key = scan.next();
                    CRD.delete(key);
                    break;
                case 4:
                    System.exit(1);
            }

        }
    }
}
