package facilities.buildings;

public class Lab extends Resource {

    Lab(String name){
        super(name);
    }
    public int getCapacity(){
        return (int) (5* Math.pow(2,(level+1)));
    }
    public int getUpgradeCost(){
        return 300*(level+1);
    }
}
