package sim;

import university.Staff;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
/**
 * This class has the purpose of reading a staff config file
 * and outputting a list of staff objects created
 */
public class TeacherReader {


    BufferedReader reader;

    /**
     * TeacherReader constructor
     * Initialises the BufferedReader object
     * @param FileName String: name of staff config file to be read
     * @throws FileNotFoundException if file does not exist
     */
    public TeacherReader(String FileName) throws FileNotFoundException {
        FileReader f = new FileReader(FileName);
        reader = new BufferedReader(f);
    }

    /**
     * Reads a single line of the file
     * @return String
     */
    public String getLine(){
        try{
            return reader.readLine();
        }
        catch (Exception e){
            System.err.println(e.toString());
        }
        return null;
    }

    /**
     * Checks if the Staff config file is ready to be read
     * @return boolean
     */
    public boolean fileIsReady(){
        try {
            return  reader.ready();
        }
        catch (Exception e){
            System.err.println(e.toString());
            return false;
        }
    }

    /**
     * Reads the Staff config file and returns the list of Staff objects in contains
     * @return ArrayList<Staff>
     * @throws Exception if input file is in incorrect format
     */
    public ArrayList<Staff> getStaff() throws Exception {
        ArrayList<Staff> staff = new ArrayList<Staff>();
        try{
            String line = getLine();
            //loop through every line in the file and split each
            // line into name and skill. Then, create new staff
            // object with these properties and add this object
            // to the list of staff
            while (line != null) {
                line = line.replace("(",":");
                line = line.replace(")","");
                String[] splitLine = line.split(":");
                staff.add(new Staff(splitLine[0],Integer.parseInt(splitLine[1])));
                line = getLine();
            }
        }
        catch (Exception e){
            throw new Exception("Error: input file has incorrect format");
        }
        return staff;
    }
}
