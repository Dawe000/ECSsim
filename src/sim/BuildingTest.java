package sim;

import facilities.Facility;
import facilities.buildings.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuildingTest {

    /**
     * The BuildingTest class creates a tree of possibilities of what
     * can be built and upgraded, then chooses the most optimal option.
     * Certain limitations have been imposed, such as a maximum of 3
     * new buildings to promote upgrades (as before the algorithm tended
     * to only build new buildings rather than upgrade existing ones) and
     * a maximum of 5 buildings and upgrades in total as any more would
     * slow down the program significantly. As a result the algorithm is
     * run multiple times in a row to make use of all available funds
     */
    ArrayList<Facility> unUpgradeable;
    ArrayList<Facility> upgradeable;
    public String[] built;
    public String[] upgraded;
    ArrayList<BuildingTest> children;
    boolean[] hallUpgrades;
    boolean[] labUpgrades;
    boolean[] theatreUpgrades;
    float budget;

    /**
     * Constructor for when the BuildingTest object is a child of another BuildingTest object
     * @param unUpgradeable ArrayList<Facility>: List of facilities which cannot be upgraded this year
     * @param upgradeable ArrayList<Facility>: List of facilities that can be upgraded this year
     * @param built String[]: Array of building types which have been built this year
     * @param upgraded String[]: Array of names of buildings which have been upgraded this year
     * @param budget float: Remaining EcsCoins for new buildings and upgrades
     */
    BuildingTest(ArrayList<Facility> unUpgradeable, ArrayList<Facility> upgradeable, String[] built, String[] upgraded, float budget){
        hallUpgrades= new boolean[]{true,true,true};
        labUpgrades= new boolean[]{true,true,true,true};
        theatreUpgrades= new boolean[]{true,true,true,true,true};
        this.unUpgradeable = new ArrayList<>();
        this.upgradeable = new ArrayList<>();
        children = new ArrayList<>();

        for (Facility f : unUpgradeable){ //clones all unupgradeable
            this.unUpgradeable.add(f.clone());
        }
        for (Facility f : upgradeable){ //clones all upgradeable
            this.upgradeable.add(f.clone());
        }
        this.built=built.clone();
        this.upgraded=upgraded.clone();
        this.budget = budget;
    }

    /**
     * Constructor for the top of the BuildingTest tree
     * @param currentFacilities Facility[]: array of all currently existing facilities in the University
     * @param budget float: budget which can be used for new buildings or for upgrading
     */
    public BuildingTest(Facility[] currentFacilities, float budget){
        hallUpgrades= new boolean[]{true,true,true};
        labUpgrades= new boolean[]{true,true,true,true};
        theatreUpgrades= new boolean[]{true,true,true,true,true};
        this.unUpgradeable = new ArrayList<>();
        this.upgradeable = new ArrayList<>();
        children = new ArrayList<>();
        this.upgraded = new String[0];
        this.built = new String[0];
        for (Facility f : currentFacilities){
            if (((Resource)f).getUpgradeCost()==-1) unUpgradeable.add(f.clone());
            else upgradeable.add(f.clone());
        }
        this.budget=budget;
    }

    /**
     * Recursive algorithm which creates children of every possible build and upgrade
     * and runs itself on those children. Note that it will only try each level upgrade
     * a single building of each class and level on each node to reduce the number of
     * identical nodes and speed the program up. Additionally, the max number of upgrades
     * is limited to 5 in order to stop hundreds of thousands of nodes being created
     * causing an overflow
     */
    public void getPossibilities(){
        if (built.length==3||upgraded.length==4||upgraded.length+built.length==5) return; //stop here if too many builds, upgrades or both


        //next 3 if statements create subtrees where the next selection was a hall, lab or theatre,
        //first checking if this is within the budget of course. I will only comment the first as
        //the other two are mostly the same

        if (budget >= 100){ //build new hall
            //create new hall objects and new lists for the upgraded and built
            Hall f = new Hall("Hall");
            List<String> newBuilt = new ArrayList<>(Arrays.asList(built));
            newBuilt.add(f.getName());
            ArrayList<Facility> newUpgradeable = new ArrayList<>();
            //clone all upgradeable and add them to the new list
            for (Facility ff : upgradeable) {
                newUpgradeable.add(ff.clone());
            }
            //clone all unupgradeable and add them to the new list
            ArrayList<Facility> newUnUpgradeable = new ArrayList<>();
            for (Facility ff : unUpgradeable) {
                newUnUpgradeable.add(ff.clone());
            }
            //add the new hall to unupgradeable as it has just been built
            Hall newHall = f.clone();
            newUnUpgradeable.add(newHall);
            //create new BuildingTest object with the new hall and run this method on it
            BuildingTest newTest =
                    new BuildingTest(
                            newUnUpgradeable,
                            newUpgradeable,
                            newBuilt.toArray(String[]::new),
                            upgraded.clone(),
                            budget-100);
            children.add(newTest); //add the new BuildingTest instance to children of this instance
            newTest.getPossibilities();
        }
        if (budget >= 300){ //build new lab
            Lab f = new Lab("Lab");
            List<String> newBuilt = new ArrayList<>(Arrays.asList(built));
            newBuilt.add(f.getName());
            ArrayList<Facility> newUpgradeable = new ArrayList<>();
            for (Facility ff : upgradeable) {
                newUpgradeable.add(ff.clone());
            }
            ArrayList<Facility> newUnUpgradeable = new ArrayList<>();
            for (Facility ff : unUpgradeable) {
                newUnUpgradeable.add(ff.clone());
            }
            Lab newLab = f.clone();
            newUnUpgradeable.add(newLab);
            BuildingTest newTest =
                    new BuildingTest(
                            newUnUpgradeable,
                            newUpgradeable,
                            newBuilt.toArray(String[]::new),
                            upgraded.clone(),
                            budget-300);
            children.add(newTest);
            newTest.getPossibilities();
        }
        if (budget >= 200){ //build new theatre
            Theatre f = new Theatre("Theatre");
            List<String> newBuilt = new ArrayList<>(Arrays.asList(built));
            newBuilt.add(f.getName());
            ArrayList<Facility> newUpgradeable = new ArrayList<>();
            for (Facility ff : upgradeable) {
                newUpgradeable.add(ff.clone());
            }
            ArrayList<Facility> newUnUpgradeable = new ArrayList<>();
            for (Facility ff : unUpgradeable) {
                newUnUpgradeable.add(ff.clone());
            }
            Theatre newTheatre = f.clone();
            newUnUpgradeable.add(newTheatre);
            BuildingTest newTest =
                    new BuildingTest(
                            newUnUpgradeable,
                            newUpgradeable,
                            newBuilt.toArray(String[]::new),
                            upgraded.clone(),
                            budget-200);
            children.add(newTest);
            newTest.getPossibilities();
        }



        // loop through possible upgrades
        //only one of each level of each building gets upgraded as to not
        //waste computer power on branches that are practically identical
        for (Facility f : upgradeable) { //loop through all buildings, I
            //will only comment the first as the other two are mostly the same
          if (f instanceof Hall) { //upgrade a hall
              //check if affordable and if a hall this level has already been upgraded
            if ((((Hall) f).getUpgradeCost() <= budget)&&(hallUpgrades[((Hall) f).getLevel()-1])) {
                //create new lists for the upgraded and built and clone all
                //the current upgraded and built into the new one
              hallUpgrades[((Hall) f).getLevel()-1] = false;
              List<String> newUpgraded = new ArrayList<>(Arrays.asList(upgraded));
              newUpgraded.add(f.getName());
              ArrayList<Facility> newUpgradeable = new ArrayList<>();
              for (Facility ff : upgradeable) {
                if (ff != f) newUpgradeable.add(ff.clone());
              }
              ArrayList<Facility> newUnUpgradeable = new ArrayList<>();
              for (Facility ff : unUpgradeable) {
                newUnUpgradeable.add(ff.clone());
              }
              //clone the hall object and upgrade it
              Hall newHall = ((Hall) f).clone();
              newHall.increaseLevel();
              newUnUpgradeable.add(newHall);
                //create new BuildingTest object with the new hall and run this method on it
              BuildingTest newTest =
                  new BuildingTest(
                      newUnUpgradeable,
                      newUpgradeable,
                      built.clone(),
                      newUpgraded.toArray(String[]::new),
                      budget - ((Hall) f).getUpgradeCost());
              children.add(newTest);//add new BuildingTest instance to children of this instance
              newTest.getPossibilities();
            }
          }
          else if (f instanceof Lab) { //upgrade a lab
            if ((((Lab) f).getUpgradeCost() <= budget)&&(labUpgrades[((Lab) f).getLevel()-1])) {
              labUpgrades[((Lab) f).getLevel()-1] = false;
              List<String> newUpgraded = new ArrayList<>(Arrays.asList(upgraded));
              newUpgraded.add(f.getName());
              ArrayList<Facility> newUpgradeable = new ArrayList<>();
              for (Facility ff : upgradeable) {
                if (ff != f) newUpgradeable.add(ff.clone());
              }
              ArrayList<Facility> newUnUpgradeable = new ArrayList<>();
              for (Facility ff : unUpgradeable) {
                newUnUpgradeable.add(ff.clone());
              }
              Lab newLab = ((Lab) f).clone();
              newLab.increaseLevel();
              newUnUpgradeable.add(newLab);
              BuildingTest newTest =
                  new BuildingTest(
                      newUnUpgradeable,
                      newUpgradeable,
                      built.clone(),
                      newUpgraded.toArray(String[]::new),
                      budget - ((Lab) f).getUpgradeCost());
              children.add(newTest);
              newTest.getPossibilities();
            }
          }
          else if (f instanceof Theatre) { //upgrade a theatre
            if ((((Theatre) f).getUpgradeCost() <= budget)&&(theatreUpgrades[((Theatre) f).getLevel()-1])) {
              theatreUpgrades[((Theatre) f).getLevel()-1] = false;
              List<String> newUpgraded = new ArrayList<>(Arrays.asList(upgraded));
              newUpgraded.add(f.getName());
              ArrayList<Facility> newUpgradeable = new ArrayList<>();
              for (Facility ff : upgradeable) {
                if (ff != f) newUpgradeable.add(ff.clone());
              }
              ArrayList<Facility> newUnUpgradeable = new ArrayList<>();
              for (Facility ff : unUpgradeable) {
                newUnUpgradeable.add(ff.clone());
              }
              Theatre newTheatre = ((Theatre) f).clone();
              newTheatre.increaseLevel();
              newUnUpgradeable.add(newTheatre);
              BuildingTest newTest =
                  new BuildingTest(
                      newUnUpgradeable,
                      newUpgradeable,
                      built.clone(),
                      newUpgraded.toArray(String[]::new),
                      budget - ((Theatre) f).getUpgradeCost());
              children.add(newTest);
              newTest.getPossibilities();
            }
          }
        }
    }

    /**
     * Uses findFinal() and determineLowest() to find the option with the
     * highest number of students. If there is a draw, the option with the
     * higher remaining budget is selected
     * @return BuildingTest: optimal result of the algorithm
     */
    public BuildingTest findBestCount(){
        ArrayList<BuildingTest> finalResults= findFinal(); //get all options
        BuildingTest highest = finalResults.get(0);
        int highestValue = determineLowest(highest);
        float remainingBudget = highest.budget;

        for (BuildingTest b : finalResults){//loop through all options
            if (b.determineLowest(b)>highestValue){ //if the new population is higher than the current, replace it
                highest = b;
                highestValue = b.determineLowest(b);
                remainingBudget = b.budget;
            }
            //if the populations are the same but one uses less money, replace it
            else if ((b.determineLowest(b)==highestValue)&&(b.budget>remainingBudget)){
                highest = b;
                highestValue = b.determineLowest(b);
                remainingBudget = b.budget;
            }

        }
        return highest;
    }

    /**
     * Takes a BuildingTest object and determines which of its capacities
     * (halls, labs or theatres) is the lowest
     * @param b BuildingTest: instance to be checked
     * @return int: capacity
     */
    public int determineLowest(BuildingTest b){
        int hall = 0;
        int lab = 0;
        int theatre = 0;

        for (Facility f : b.unUpgradeable){
            if (f instanceof Hall) hall += ((Hall)f).getCapacity();
            else if (f instanceof Lab) lab += ((Lab)f).getCapacity();
            else if (f instanceof Theatre) theatre += ((Theatre)f).getCapacity();
        }

        for (Facility f : b.upgradeable){
            if (f instanceof Hall) hall += ((Hall)f).getCapacity();
            else if (f instanceof Lab) lab += ((Lab)f).getCapacity();
            else if (f instanceof Theatre) theatre += ((Theatre)f).getCapacity();
        }
        return Math.min(Math.min(hall,lab),theatre);
    }

    /**
     * Creates a new ArrayList<> and runs the other findFinal(). This recursively
     * find all the BuildingTest leaf nodes (nodes without children)
     * @return ArrayList<BuildingTest>: all final results of the algorithm
     */
    public ArrayList<BuildingTest> findFinal(){
        return findFinal(new ArrayList<>());
    }

    /**
     * Recursively finds the leaf nodes using a depth first search
     * @param list: list of currently found leaf nodes
     * @return ArrayList<BuildingTest>: list of all leaf nodes for
     * this BuildingTest instance
     */
    public ArrayList<BuildingTest> findFinal(ArrayList<BuildingTest> list){
        if (children.isEmpty()) list.add(this);
        else {
            for (BuildingTest b : children) {
                b.findFinal(list);
            }
        }

        return list;
    }
}
