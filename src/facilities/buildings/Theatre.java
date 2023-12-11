package facilities.buildings;

public class Theatre extends Resource {

    Theatre(String name){
        super(name);
    }
    public int getCapacity(){
        return (int) (10* Math.pow(2,(level-1)));
    }
    public int getUpgradeCost(){
        if (level == 6) return -1;
        else return 200*(level+1);
    }
}
