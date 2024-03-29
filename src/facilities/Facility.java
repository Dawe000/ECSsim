package facilities;

public abstract class Facility {

    /**
     * Abstract class for buildings with getter for name, constructor and abstract clone function
     */
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
