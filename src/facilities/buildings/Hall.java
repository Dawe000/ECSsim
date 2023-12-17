package facilities.buildings;

public class Hall extends Resource {
    /**
     * Constructor for Hall
     * @param name String: name of the Hall
     */
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
