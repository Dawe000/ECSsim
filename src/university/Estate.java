package university;

import facilities.Facility;
import facilities.buildings.*;

import java.util.ArrayList;
/**
 * This class administrates all buildings that
 * are owned by the University
 */
public class Estate {

    ArrayList<Facility> facilities;

    /**
     * Constructor for Estate
     */
    Estate(){
        facilities = new ArrayList<Facility>();
    }

    /**
     * Outputs all facilities owned by the University
     * @return Facility[]: facilities owned by the University
     */
    public Facility[] getFacilities(){
        return facilities.toArray(Facility[]::new);
    }

    /**
     * Adds a new facility to the Estate
     * @param type String: Hall, Lab or Theatre
     * @param name String: name for the new buidling
     * @return Facility: new building
     */
    Facility addFacility(String type, String name) throws Exception {
        switch (type){
            case "Hall":
                Hall newHall = new Hall(name);
                facilities.add(newHall);
                return newHall;
            case "Lab":
                Lab newLab = new Lab(name);
                facilities.add(newLab);
                return newLab;
            case "Theatre":
                Theatre newTheatre = new Theatre(name);
                facilities.add(newTheatre);
                return newTheatre;
            default:
                throw new Exception("This is not a type of facility.");
        }
    }

    /**
     * Finds the total maintenance cost of all the buildings
     * @return float: total cost
     */
    public float getMaintenanceCost(){
        int total = 0;
        for (Facility f : facilities){
            total += ((Resource)f).getCapacity();
        }
        return (float)(total * 0.1);
    }

    /**
     * Gets the total number of students which is the smallest of the
     * total Hall, Lab or Estate population
     * @return int: number of students
     */
    public int getNumberOfStudents(){
        int hall = 0;
        int lab = 0;
        int theatre = 0;

        for (Facility f : facilities){
            if (f instanceof Hall) hall += ((Hall)f).getCapacity();
            else if (f instanceof Lab) lab += ((Lab)f).getCapacity();
            else if (f instanceof Theatre) theatre += ((Theatre)f).getCapacity();
        }
        return Math.min(Math.min(hall,lab),theatre);

    }
}
