package sim;

import university.HumanResource;
import university.Staff;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class TeacherSelect {
    HumanResource humanResource;
    ArrayList<Staff> market;
    int currentNewTeacher;

    public TeacherSelect(HumanResource humanResource, ArrayList<Staff> market){
        this.humanResource = humanResource;
        this.market = market;
        currentNewTeacher = 1;
    }

    public ArrayList<Staff> hire(float budget,int students){
        ArrayList<Staff> hires = new ArrayList<Staff>();
        Random r = new Random();
        int n = 0;
        while (humanResource.staffSalary.size()*30<students){
            n = r.nextInt(market.size());
            if (market.toArray(Staff[]::new)[n].skill*0.105<=budget){
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

    public Staff generateStaff(){
        Random r = new Random();
        int n = r.nextInt(100)+1;
        Staff s = new Staff("Teacher "+currentNewTeacher,n);
        currentNewTeacher++;
        return s;
    }

    public void instruct(int students){
        Iterator<Staff> i = humanResource.getStaff();
        int per = students/humanResource.staffSalary.size();
        int rem = students%humanResource.staffSalary.size();
        i.next().instruct(per+rem);

        while (i.hasNext()){
            i.next().instruct(per);

        }
    }

    public Staff[] loseStaff(){
        Random r = new Random();
        ArrayList<Staff> lostStaff = new ArrayList<Staff>();
        Iterator<Staff> i = humanResource.getStaff();
        while(i.hasNext()){
            Staff s = i.next();
            s.yearsOfTeaching++;
            if (s.stamina<r.nextFloat()*100){
                market.add(s);
                lostStaff.add(s);
            }
            s.replenishStamina();
            if (s.yearsOfTeaching==30){
                lostStaff.add(s);
            }

        }
        for (Staff s : lostStaff){
            humanResource.staffSalary.remove(s);
        }
        return lostStaff.toArray(Staff[]::new);
    }


}
