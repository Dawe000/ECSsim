import decision.BuildingTest;
import facilities.Facility;
import facilities.buildings.Building;
import facilities.buildings.Hall;
import facilities.buildings.Lab;
import university.Staff;
import university.University;

import java.util.ArrayList;

public class EcsSim {
    University university;
    ArrayList<Staff> staffMarket;
    int currentNewBuilding;

    EcsSim(int funding, ArrayList<Staff> staff){
        university = new University(funding);
        staffMarket = staff;
        currentNewBuilding = 1;

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


        university.budget -= university.estate.getMaintenanceCost();
        System.out.println("Budget after maintenance is "+ university.getBudget());

    }

    void Simulate(int years) throws Exception {
        for (int i = 1; i<years+1; i++){
            System.out.println("\nYEAR"+i);
            Simulate();
        }
    }

}
