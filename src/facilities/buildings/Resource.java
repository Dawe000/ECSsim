package facilities.buildings;

import facilities.Facility;

/**
 * Abstract class for buildings
 */
public abstract class Resource extends Facility implements Building{

    int level;

    /**
     * Constructor for building sets the name to the input and sets the level to 1
     * @param name String: name of the new building
     */
    Resource(String name){
        super(name);
        level = 1;
    }

    /**
     * Increases the level of the building by one
     */
    public void increaseLevel(){
        level++;
    }

    /**
     * Returns the level of the building
     * @return int: level
     */
    public int getLevel(){
        return level;
    }



}
