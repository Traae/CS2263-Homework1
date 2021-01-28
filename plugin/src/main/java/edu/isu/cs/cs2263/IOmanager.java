package edu.isu.cs.cs2263;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class IOmanager {
    private Gson gson;
    public IOmanager(){
         gson = new GsonBuilder().setPrettyPrinting().create();
    }
    public void writeToFile(LinkedList<Student> studentList) throws IOException {


        String tofile = gson.toJson(studentList);
        Files.writeString(Paths.get("./studentinfo.json"), tofile);
    }
    public LinkedList<Student> readInFile() throws IOException {
        LinkedList<Student> studentList = new LinkedList<>();
        String readIn = "";

        readIn = Files.readString(Paths.get("./studentinfo.json"));

        Type sList = new TypeToken<ArrayList<Student>>() {}.getType();
        List<Student> toConvert = gson.fromJson(readIn, sList);

        studentList.addAll(toConvert);

        return studentList;
    }

}
