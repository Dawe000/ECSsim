package sim;

import university.Staff;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class teacherReader {
    BufferedReader reader;
    public teacherReader(String FileName) throws FileNotFoundException {
        FileReader f = new FileReader(FileName);
        reader = new BufferedReader(f);
    }
    public String getLine(){
        try{
            return reader.readLine();
        }
        catch (Exception e){
            System.err.println(e.toString());
        }
        return null;
    }
    public boolean fileIsReady(){
        try {
            return  reader.ready();
        }
        catch (Exception e){
            System.err.println(e.toString());
            return false;
        }
    }
    public ArrayList<Staff> getStaff(){
        ArrayList<Staff> staff = new ArrayList<Staff>();
        String line = getLine();
        while (line != null) {
            line = line.replace("(",":");
            line = line.replace(")","");
            String[] splitLine = line.split(":");
            staff.add(new Staff(splitLine[0],Integer.parseInt(splitLine[1])));
            line = getLine();
        }
        return staff;
    }
}
