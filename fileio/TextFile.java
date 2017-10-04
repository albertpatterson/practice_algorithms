package fileio;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by apatters on 10/3/2017.
 */
public class TextFile {

    public String data;

//    public String filePath;

//    private FileReader fileReader;
//    private BufferedReader bufferedReader;
//
//    private FileWriter fileWriter;
//    private BufferedWriter bufferedWriter;

//    public TextFile(String filePath) throws FileNotFoundException {
//        this.filePath = filePath;
//        fileReader = new FileReader(filePath);
//        bufferedReader = new BufferedReader(fileReader);
//    }


    private File createFile(String filePath) {
        File file = new File(filePath);

        if (!file.exists()) {
            File parent = file.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
        }
        return file;
    }

    public void writeln(String filePath, String data) throws IOException {

        File checker = new File(filePath);

        if(checker.exists()){
            data = String.format("\n%s", data);
        }

        File file = createFile(filePath);

        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(data);
        bufferedWriter.close();
    }

    public void write(String filePath, String data,  boolean append) throws IOException {
        File file = createFile(filePath);
        FileWriter fileWriter = new FileWriter(file, append);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write(data);

        bufferedWriter.close();
    }

    public void write(String filePath, String data) throws IOException {
        write(filePath, data, false);
    }

    public String read(String filePath) throws FileNotFoundException {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        StringBuilder out = new StringBuilder();
        String line;
        try {
            while((line = bufferedReader.readLine())!=null){
                out.append(line);
            }

            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return out.toString();
    }

    public ArrayList<String> readlns(String filePath) throws FileNotFoundException {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        ArrayList<String> lines = new ArrayList<>();

        String line;
        try {
            while((line = bufferedReader.readLine())!=null){
                lines.add(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

}
