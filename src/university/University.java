package university;

import facilities.Facility;
import facilities.buildings.Building;

/**
 * The University class manages the buildings and human
 * resource of the University through the classes that
 * it is composed of
 */
public class University {
    public float budget;
    public Estate estate;
    public int reputation;
    public HumanResource humanResource;

    /**
     * Constructor for University
     * @param funding int: starting budget for the University
     */
    public University(int funding){
        budget = funding;
        estate = new Estate();
        reputation = 0;
        humanResource = new HumanResource();
    }

    /**
     * Checks if a new facility can be afforded,
     * dds a new Facility through the Estate class,
     * reduces budget and increases reputation
     * @param type String: Hall, Lab or Theatre
     * @param name String: name for the new building
     * @return Facility: new facility
     * @throws Exception
     */
    public Facility build(String type,String name) throws Exception {
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

    /**
     * Increases the University budget based on the number of students
     */
    public void fund(){
        budget+=estate.getNumberOfStudents()*10;
    }

    /**
     * Checks if a building is max level, then if an upgrade
     * for it can be afforded, then upgrades it
     * @param building Building: building instance to be upgraded
     * @throws Exception
     */
    public void upgrade(Building building) throws Exception{
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

    /**
     * Getter for budget
     * @return float: budget of the University
     */
    public float getBudget() {
        return budget;
    }

    /**
     * Getter for reputation
     * @return int: reputation of the University
     */
    public int getReputation() {
        return reputation;
    }
}
