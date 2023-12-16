import sim.BuildingTest;
import sim.TeacherSelect;
import facilities.Facility;
import facilities.buildings.Building;
import facilities.buildings.Hall;
import facilities.buildings.Lab;
import sim.teacherReader;
import university.Staff;
import university.University;

import java.util.ArrayList;

public class EcsSim {
    University university;
    ArrayList<Staff> staffMarket;
    int currentNewBuilding;
    TeacherSelect teacherSelect;

    public static void main(String[] args) throws Exception {
        if (args.length!=3) throw new Exception("Error: should have 3 arguments");
        teacherReader t = new teacherReader(args[0]);
        if (!t.fileIsReady()) throw new Exception("Error: file name invalid");
        try {
            Integer.parseInt(args[1]);
        }
        catch (Exception e){
            throw new Exception("Second argument should be an integer");
        }
        try {
            Integer.parseInt(args[2]);
        }
        catch (Exception e){
            throw new Exception("Third argument should be an integer");
        }
        ArrayList<Staff> staff = t.getStaff();
        EcsSim sim = new EcsSim(Integer.parseInt(args[1]),staff);
        sim.Simulate(Integer.parseInt(args[2]));
    }

    EcsSim(int funding, ArrayList<Staff> staff){
        university = new University(funding);
        staffMarket = staff;
        currentNewBuilding = 1;
        teacherSelect = new TeacherSelect(university.humanResource, staffMarket);
        while (staffMarket.size()<10) staffMarket.add(teacherSelect.generateStaff());
    }

    void Simulate() throws Exception {
        int oldPop = university.estate.getNumberOfStudents();
        System.out.println("Start budget is "+university.getBudget()+"\n");
        //upgrade and build new buildings
        BuildingTest buildingTest = new BuildingTest(university.estate.getFacilities(),university.getBudget());
        buildingTest.getPossibilities();
        BuildingTest result = buildingTest.findBestCount();
        boolean check = (result.determineLowest(result)==oldPop);
        while (result.determineLowest(result)!=oldPop){
            for (String s : result.upgraded){
                for (Facility f : university.estate.getFacilities()){
                    if (f.getName().equals(s)){
                        university.upgrade((Building) f);
                        String type="Theatre";
                        if(f instanceof Hall) type = "Hall";
                        if(f instanceof Lab) type = "Lab";

                        System.out.println(type+" "+f.getName()+" upgraded to level "+((Building) f).getLevel());
                        break;
                    }
                }
            }
            for (String s : result.built){
                university.build(s,"Building "+ currentNewBuilding);
                System.out.println("New " + s + " Building " + currentNewBuilding+ " has been built" );
                currentNewBuilding++;
            }
            oldPop = university.estate.getNumberOfStudents();
            buildingTest = new BuildingTest(university.estate.getFacilities(),university.getBudget());
            buildingTest.getPossibilities();
            result = buildingTest.findBestCount();


        }
        if (check){
            System.out.println("\nNo buildings built or upgraded this year");
        }
        else{
            System.out.println("\nBudget after building and upgrading is "+university.getBudget());
        }

        university.fund();
        System.out.println("Budget after student contributions is "+ university.getBudget());

        if (university.humanResource.staffSalary.size()*40<university.estate.getNumberOfStudents()){
            System.out.println(" ");
            Staff[] hires = teacherSelect.hire((float) (university.getBudget()*0.1-university.humanResource.getTotalSalary()),university.estate.getNumberOfStudents()).toArray(Staff[]::new);
            for (Staff s : hires){
                System.out.println("Hired "+s.name+", with skill " + s.skill);
            }
        }


        System.out.println(" ");
        teacherSelect.instruct(university.estate.getNumberOfStudents());
        university.reputation += university.estate.getNumberOfStudents();
        System.out.println(university.estate.getNumberOfStudents()+" students instructed this year");

        System.out.println(" ");
        university.budget -= university.estate.getMaintenanceCost();
        System.out.println("Budget after maintenance is "+ university.getBudget());

        System.out.println(" ");
        university.budget -= university.humanResource.getTotalSalary();
        System.out.println("Budget after salary is "+ university.getBudget());

        System.out.println(" ");
        Staff[] lostStaff = teacherSelect.loseStaff();
        for (Staff s : lostStaff){
            if(s.yearsOfTeaching==30){
                System.out.println(s.name+ " has retired");
            }
            else{
                System.out.println(s.name+ " has left");
            }
        }
        System.out.println(" ");
        System.out.println("Reputation at the end of this year is "+university.getReputation());

    }

    void Simulate(int years) throws Exception {
        for (int i = 1; i<years+1; i++){
            System.out.println("\nYEAR"+i);
            Simulate();
        }
    }

}
