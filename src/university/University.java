package university;

import facilities.Facility;
import facilities.buildings.Building;
import facilities.buildings.Hall;

public class University {
    float budget;
    public Estate estate;
    int reputation;
    public HumanResource humanResource;

    public University(int funding){
        budget = funding;
        estate = new Estate();
        reputation = 0;
        humanResource = new HumanResource();
    }

    Facility build(String type,String name){
        switch (type){
            case "Hall":
                if (budget>=100) {
                    Facility newFacility = estate.addFacility(type,name);
                    budget-=100;
                    reputation+=100;
                    return newFacility;
                }
                else{
                    System.out.println("Insufficient funds");
                    return null;
                }
            case "Lab":
                if (budget>=300) {
                    Facility newFacility = estate.addFacility(type,name);
                    budget-=300;
                    reputation+=100;
                    return newFacility;
                }
                else{
                    System.out.println("Insufficient funds");
                    return null;
                }
            case "Theatre":
                if (budget>=200) {
                    Facility newFacility = estate.addFacility(type,name);
                    budget-=200;
                    reputation+=100;
                    return newFacility;
                }
                else{
                    System.out.println("Insufficient funds");
                    return null;
                }
            default:
                System.out.println("Invalid building type");
                return null;
        }


    }

    void upgrade(Building building) throws Exception{
        boolean check = false;
        for (Facility b : estate.getFacilities()){
            if (b == building) {check = true; break;}
        }

        if (check){
            if (building.getUpgradeCost() == -1){
                throw new Exception("Error: Building level is maxxed");
            }
            else{
                if (building.getUpgradeCost() <= budget){
                    budget -= building.getUpgradeCost();
                    building.increaseLevel();
                    reputation += 50;
                }
                else{
                    System.out.println("Insufficient funds");
                }
            }

        }
        else throw new Exception("Error: Building not a part of the University");
    }

    public float getBudget() {
        return budget;
    }

    public int getReputation() {
        return reputation;
    }
}
