package facilities;

public class Facility {
    String name;

    protected Facility(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
