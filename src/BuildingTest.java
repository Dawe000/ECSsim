import facilities.Facility;
import facilities.buildings.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuildingTest {
    ArrayList<Facility> unUpgradeable;
    ArrayList<Facility> upgradeable;
    String[] built;
    String[] upgraded;
    ArrayList<BuildingTest> children;
    float budget;

    BuildingTest(ArrayList<Facility> unUpgradeable, ArrayList<Facility> upgradeable, String[] built, String[] upgraded, float budget){
        this.unUpgradeable = new ArrayList<Facility>();
        this.upgradeable = new ArrayList<Facility>();
        children = new ArrayList<BuildingTest>();

        for (Facility f : unUpgradeable){
            this.unUpgradeable.add(f.clone());
        }
        for (Facility f : upgradeable){
            this.upgradeable.add(f.clone());
        }
        this.built=built.clone();
        this.upgraded=upgraded.clone();
        this.budget = budget;
    }

    BuildingTest(Facility[] currentFacilities, float budget){
        this.unUpgradeable = new ArrayList<Facility>();
        this.upgradeable = new ArrayList<Facility>();
        children = new ArrayList<BuildingTest>();
        this.upgraded = new String[0];
        this.built = new String[0];
        for (Facility f : currentFacilities){
            if (((Resource)f).getUpgradeCost()==-1) unUpgradeable.add(f.clone());
            else upgradeable.add(f.clone());
        }
        this.budget=budget;
    }

    public void getPossibilities(){
        if (budget >= 100){ //build new hall
            Hall f = new Hall("test");
            List<String> newBuilt = new ArrayList<String>(Arrays.asList(built));
            newBuilt.add(f.getName());
            ArrayList<Facility> newUpgradeable = new ArrayList<Facility>();
            for (Facility ff : upgradeable) {
                newUpgradeable.add(ff.clone());
            }
            ArrayList<Facility> newUnUpgradeable = new ArrayList<Facility>();
            for (Facility ff : unUpgradeable) {
                newUnUpgradeable.add(ff.clone());
            }
            Hall newHall = ((Hall) f).clone();
            newUnUpgradeable.add(newHall);
            BuildingTest newTest =
                    new BuildingTest(
                            newUnUpgradeable,
                            newUpgradeable,
                            newBuilt.toArray(String[]::new),
                            upgraded.clone(),
                            budget-100);
            children.add(newTest);
            newTest.getPossibilities();
        }
        if (budget >= 300){ //build new lab
            Lab f = new Lab("test");
            List<String> newBuilt = new ArrayList<String>(Arrays.asList(built));
            newBuilt.add(f.getName());
            ArrayList<Facility> newUpgradeable = new ArrayList<Facility>();
            for (Facility ff : upgradeable) {
                newUpgradeable.add(ff.clone());
            }
            ArrayList<Facility> newUnUpgradeable = new ArrayList<Facility>();
            for (Facility ff : unUpgradeable) {
                newUnUpgradeable.add(ff.clone());
            }
            Lab newLab = ((Lab) f).clone();
            newUnUpgradeable.add(newLab);
            BuildingTest newTest =
                    new BuildingTest(
                            newUnUpgradeable,
                            newUpgradeable,
                            newBuilt.toArray(String[]::new),
                            upgraded.clone(),
                            budget-100);
            children.add(newTest);
            newTest.getPossibilities();
        }
        if (budget >= 200){ //build new theatre
            Theatre f = new Theatre("test");
            List<String> newBuilt = new ArrayList<String>(Arrays.asList(built));
            newBuilt.add(f.getName());
            ArrayList<Facility> newUpgradeable = new ArrayList<Facility>();
            for (Facility ff : upgradeable) {
                newUpgradeable.add(ff.clone());
            }
            ArrayList<Facility> newUnUpgradeable = new ArrayList<Facility>();
            for (Facility ff : unUpgradeable) {
                newUnUpgradeable.add(ff.clone());
            }
            Theatre newTheatre = ((Theatre) f).clone();
            newUnUpgradeable.add(newTheatre);
            BuildingTest newTest =
                    new BuildingTest(
                            newUnUpgradeable,
                            newUpgradeable,
                            newBuilt.toArray(String[]::new),
                            upgraded.clone(),
                            budget-100);
            children.add(newTest);
            newTest.getPossibilities();
        }

    // loop through possible upgrades
        for (Facility f : upgradeable) {
          if (f instanceof Hall) { //upgrade a hall
            if (((Hall) f).getUpgradeCost() <= budget) {
              List<String> newUpgraded = new ArrayList<String>(Arrays.asList(upgraded));
              newUpgraded.add(f.getName());
              ArrayList<Facility> newUpgradeable = new ArrayList<Facility>();
              for (Facility ff : upgradeable) {
                if (ff != f) newUpgradeable.add(ff.clone());
              }
              ArrayList<Facility> newUnUpgradeable = new ArrayList<Facility>();
              for (Facility ff : unUpgradeable) {
                newUnUpgradeable.add(ff.clone());
              }
              Hall newHall = ((Hall) f).clone();
              newHall.increaseLevel();
              newUnUpgradeable.add(newHall);
              BuildingTest newTest =
                  new BuildingTest(
                      newUnUpgradeable,
                      newUpgradeable,
                      built.clone(),
                      newUpgraded.toArray(String[]::new),
                      budget - ((Hall) f).getUpgradeCost());
              children.add(newTest);
              newTest.getPossibilities();
            }
          }
          else if (f instanceof Lab) { //upgrade a lab
            if (((Lab) f).getUpgradeCost() <= budget) {
              List<String> newUpgraded = new ArrayList<String>(Arrays.asList(upgraded));
              newUpgraded.add(f.getName());
              ArrayList<Facility> newUpgradeable = new ArrayList<Facility>();
              for (Facility ff : upgradeable) {
                if (ff != f) newUpgradeable.add(ff.clone());
              }
              ArrayList<Facility> newUnUpgradeable = new ArrayList<Facility>();
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
            if (((Theatre) f).getUpgradeCost() <= budget) {
              List<String> newUpgraded = new ArrayList<String>(Arrays.asList(upgraded));
              newUpgraded.add(f.getName());
              ArrayList<Facility> newUpgradeable = new ArrayList<Facility>();
              for (Facility ff : upgradeable) {
                if (ff != f) newUpgradeable.add(ff.clone());
              }
              ArrayList<Facility> newUnUpgradeable = new ArrayList<Facility>();
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
    public BuildingTest findBestCount(){
        ArrayList<BuildingTest> finalResults= findFinal();
        BuildingTest highest = finalResults.get(0);
        int highestValue = determineLowest(highest);
        float remainingBudget = highest.budget;
        for (BuildingTest b : finalResults){
            if (determineLowest(b)>highestValue){
                highest = b;
                highestValue = determineLowest(b);
                remainingBudget = b.budget;
            }
            else if ((determineLowest(b)==highestValue)||(b.budget>remainingBudget)){
                highest = b;
                highestValue = determineLowest(b);
                remainingBudget = b.budget;
            }

        }
        return highest;
    }

    int determineLowest(BuildingTest b){
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


    public ArrayList<BuildingTest> findFinal(){
        return findFinal(new ArrayList<BuildingTest>());
    }

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
