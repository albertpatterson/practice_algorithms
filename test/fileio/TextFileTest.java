package fileio;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by apatters on 10/3/2017.
 */
public class TextFileTest {
    private String filePath = "junk/file.txt";

    @Before
    public void clearFile(){
        File file = new File(filePath);

        if(file.exists()){
            file.delete();
        }
    }

    @Test
    public void writeAndRead() throws Exception {
        TextFile textFile = new TextFile();

        String data = "testing";

        textFile.write(filePath, data);

        String readData = textFile.read(filePath);

        assertEquals(data, readData);
    }

    @Test
    public void writelnAndReadlns() throws Exception {
        TextFile textFile = new TextFile();

        String data = "testing";

        textFile.writeln(filePath, data);
        textFile.writeln(filePath, data);
        textFile.writeln(filePath, data);

        ArrayList<String> lines = textFile.readlns(filePath);

        assertEquals(3, lines.size());

        for(int i = 0; i<3; i++){
            assertEquals(data, lines.get(i));
        }

    }

}