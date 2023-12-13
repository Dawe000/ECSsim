package facilities.buildings;

public class Hall extends Resource {

    public Hall(String name){
        super(name);
    }
    public int getCapacity(){
        return (int) (6* Math.pow(2,(level-1)));
    }
    public int getUpgradeCost(){
        if (level == 4) return -1;
        else return 100*(level+1);
    }
    public Hall clone(){
        Hall newHall = new Hall(getName());
        newHall.level = this.level;
        return newHall;
    }
}
