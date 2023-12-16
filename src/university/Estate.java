package university;

import facilities.Facility;
import facilities.buildings.*;

import java.util.ArrayList;

public class Estate {
    ArrayList<Facility> facilities;

    Estate(){
        facilities = new ArrayList<Facility>();
    }
    
    public Facility[] getFacilities(){
        return facilities.toArray(Facility[]::new);
    }

    Facility addFacility(String type, String name){
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
                System.out.println("This is not a type of facility.");
                return null;
        }
    }

    public float getMaintenanceCost(){
        int total = 0;
        for (Facility f : facilities){
            total += ((Resource)f).getCapacity();
        }
        return (float)(total * 0.1);
    }

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
