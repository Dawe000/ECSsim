package sim;

import university.HumanResource;
import university.Staff;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
/**
 * This class handles all aspects of deciding what
 * staff join the school, who they instruct and if
 * they leave
 */
public class TeacherSelect {

    HumanResource humanResource;
    ArrayList<Staff> market;
    int currentNewTeacher;

    /**
     * Constructor for TeacherSelect
     * @param humanResource HumanResource: instance being used by the University
     * @param market ArrayList<Staff>: all unemployed staff members
     */
    public TeacherSelect(HumanResource humanResource, ArrayList<Staff> market){
        this.humanResource = humanResource;
        this.market = market;
        currentNewTeacher = 1;
    }

    /**
     * Randomly hires Staff based on the budget and the number of students
     * @param budget float: money allocated to hiring Staff
     * @param students int: number of students that need to be instructed
     * @return ArrayList<staff>: newly hired staff members
     */
    public ArrayList<Staff> hire(float budget,int students){
        ArrayList<Staff> hires = new ArrayList<>();
        Random r = new Random();
        int n;
        while (humanResource.staffSalary.size()*30<students){ //condition is so that the teacher to student ratio isn't too disproportionate
            n = r.nextInt(market.size());
            if (market.toArray(Staff[]::new)[n].skill*0.105<=budget){//assumes all staff are allocated the maximum salary
                humanResource.addStaff(market.toArray(Staff[]::new)[n]);
                budget -= humanResource.staffSalary.get(market.toArray(Staff[]::new)[n]);
                hires.add(market.toArray(Staff[]::new)[n]);
                market.remove(market.toArray(Staff[]::new)[n]);
                if (market.size()<10) market.add(generateStaff());

            }
            else{
                break;
            }
        }
        return hires;
    }

    /**
     * Generates a new staff member for if the market runs out of staff
     * @return Staff: new staff member
     */
    public Staff generateStaff(){
        Random r = new Random();
        int n = r.nextInt(100)+1;
        Staff s = new Staff("Teacher "+currentNewTeacher,n);
        currentNewTeacher++;
        return s;
    }

    /**
     * Splits the student population evenly amongst the Staff and calls instruct on all of them.
     * @param students int: number of students being instructed
     */
    public int instruct(int students){
        Iterator<Staff> i = humanResource.getStaff();
        int per = students/humanResource.staffSalary.size();
        int rem = students%humanResource.staffSalary.size();
        i.next().instruct(per+rem);
        int rep = 0;
        while (i.hasNext()){
            rep+=i.next().instruct(per);

        }
        return rep;
    }

    /**
     * Decides which Staff leave at the end of the year, either due to retirement
     * or due to burnout. Also increments yearsOfTeaching
     * @return Staff[] names of Staff who are leaving
     */
    public Staff[] loseStaff(){
        Random r = new Random();
        ArrayList<Staff> lostStaff = new ArrayList<>();
        Iterator<Staff> i = humanResource.getStaff();
        while(i.hasNext()){
            Staff s = i.next();
            s.increaseYearsOfTeaching();
            if (s.stamina<r.nextFloat()*100){//burnout
                market.add(s);
                lostStaff.add(s);
            }
            s.replenishStamina();
            if (s.yearsOfTeaching==30){//retirement
                lostStaff.add(s);
            }

        }
        for (Staff s : lostStaff){ //done outside the main loop to avoid java.util.ConcurrentModificationException
            humanResource.staffSalary.remove(s);
        }
        return lostStaff.toArray(Staff[]::new);
    }


}
