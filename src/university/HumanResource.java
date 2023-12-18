package university;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

/**
 * The purpose of this class is to oversee
 * all employees of the University and
 * their salaries
 */
public class HumanResource {




    public HashMap<Staff,Float> staffSalary;

    /**
     * Constructor for HumanResource
     */
    HumanResource(){
        staffSalary = new HashMap<Staff,Float>();
    }

    /**
     * Adds a member of staff and gives them a salary based on their skill
     * @param staff Staff: instance to be added to the HumanResource
     */
    public void addStaff(Staff staff){
        Random r = new Random();
        Float salary = (float) (staff.skill*((r.nextFloat()+9.5)/100));
        staffSalary.put(staff,salary);
    }

    /**
     * Returns an iterator object for the Staff objects employed by the University
     * @return Iterator<Staff>: iterator of Staff objects
     */
    public Iterator<Staff> getStaff(){
        return staffSalary.keySet().iterator();

    }

    /**
     * Returns the combined salary of all Staff members
     * @return float: salary of all staff
     */
    public float getTotalSalary(){
        float total = 0;
        Iterator<Staff> i = getStaff();
        while (i.hasNext()){
            Staff s = i.next();
            total += staffSalary.get(s);
        }
        return total;
    }

}
