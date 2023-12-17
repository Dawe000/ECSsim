import sim.TeacherReader;
import university.Staff;

import java.util.ArrayList;

public class Main {
  public static void main(String[] args) throws Exception {

        /* Test part 4
        Staff prof1 = new Staff("Albert", 23);
        Staff prof2 = new Staff("Ben", 89);
        Staff prof3 = new Staff("Carol", 44);
        Staff prof4 = new Staff("Dave", 95);
        University uni = new University(10000);

        uni.humanResource.addStaff(prof1);
        uni.humanResource.addStaff(prof2);
        uni.humanResource.addStaff(prof3);
        uni.humanResource.addStaff(prof4);
        System.out.println(uni.humanResource.getTotalSalary());

        System.out.println(" ");

        System.out.println(prof1.stamina + " " + prof1.skill);
        prof1.instruct(100);
        System.out.println(prof1.stamina + " " + prof1.skill);
        prof1.replenishStamina();
        System.out.println(prof1.stamina + " " + prof1.skill);
         */

        /*
        Lab l1 = new Lab("lab1");
        Lab l2 = new Lab("lab2");
        Lab l3 = new Lab("lab3");
        Hall h1 = new Hall("hall1");
        Hall h2 = new Hall("hall2");
        Theatre t1 = new Theatre("theatre1");
        Theatre t2 = new Theatre("theatre2");
        ArrayList<Facility> f = new ArrayList<Facility>();
        f.add(l1);
        f.add(l2);
        f.add(l3);
        f.add(h1);
        f.add(h2);
        f.add(t1);
        f.add(t2);
        Facility[] facilities = f.toArray(Facility[]::new);
        decision.BuildingTest test = new decision.BuildingTest(facilities,500);
        test.getPossibilities();
        decision.BuildingTest result = test.findBestCount();
        */
        /*
        Staff prof1 = new Staff("Albert", 23);
        Staff prof2 = new Staff("Ben", 89);
        Staff prof3 = new Staff("Carol", 44);
        Staff prof4 = new Staff("Dave", 95);
        ArrayList<Staff> staff = new ArrayList<Staff>();
        staff.add(prof1);
        staff.add(prof2);
        staff.add(prof3);
        staff.add(prof4);
        EcsSim sim = new EcsSim(1000,staff);
        sim.Simulate(50);

        */
        TeacherReader t = new TeacherReader("staff.txt");
        ArrayList<Staff> staff = t.getStaff();
        System.in.read();
    }
}