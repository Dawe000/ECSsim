package facilities.buildings;

public class Hall extends Resource {

    Hall(String name){
        super(name);
    }
    public int getCapacity(){
        return (int) (6* Math.pow(2,(level+1)));
    }
    public int getUpgradeCost(){
        return 100*(level+1);
    }
}
