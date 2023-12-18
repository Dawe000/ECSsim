package facilities;
/**
 * Abstract class for buildings with getter for name, constructor and abstract clone function
 */
public abstract class Facility {


    String name;

    protected Facility(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    /**
     * Copies an objects properties and returns the new object
     * @return Facility
     */
    abstract public Facility clone();
}
