package university;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class HumanResource {
    public HashMap<Staff,Float> staffSalary;
    HumanResource(){
        staffSalary = new HashMap<Staff,Float>();
    }

    public void addStaff(Staff staff){
        Random r = new Random();
        Float salary = (float) (staff.skill*((r.nextFloat()+9.5)/100));
        staffSalary.put(staff,salary);
    }
    public Iterator<Staff> getStaff(){
        return staffSalary.keySet().iterator();

    }
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
