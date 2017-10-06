package fileio;

import java.io.*;

/**
 * Created by apatters on 10/5/2017.
 */
public class SerializationPrac {


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        TestClass tc = new TestClass(1, 16);

        File file = new File("serialized");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos= new ObjectOutputStream(fos);

        oos.writeObject(tc);

        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ios = new ObjectInputStream(fis);

        TestClass tc2 = (TestClass)ios.readObject();
        tc2.disp();

        oos.close();
        ios.close();
    }

}

class TestClass implements Serializable {
    private int val;
    private transient int pvt;

    public TestClass(int val1, int val2){
        val = val1;
        pvt = val2;
    }

    public void disp(){
        System.out.println(val);
        System.out.println(pvt);
    }
}