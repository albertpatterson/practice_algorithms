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


    public void writeln(String filePath, String data) throws IOException {

        File file = new File(filePath);

        if(file.exists()){
            data = String.format("\n%s", data);
        }
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(data);

        bufferedWriter.close();
    }

    public void write(String filePath, String data,  boolean append) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, append);
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

}
