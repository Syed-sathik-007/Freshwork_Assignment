import Key_value_operation.CRD;

import java.io.IOException;

public class newtest {
    public static void main(String [] args) throws IOException, ClassNotFoundException {
        CRD.create("a",100,20);
        CRD.create("b",10);
        CRD.read("a");
        CRD.read("b");
        CRD.read("c");
        CRD.delete("a");
        CRD.delete("b");
        CRD.delete("c");

    }
}
