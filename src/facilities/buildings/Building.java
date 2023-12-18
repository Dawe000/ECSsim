package facilities.buildings;

/**
 * Interface for building objects
 */

public interface Building {


    /**
     * Returns level of the building
     * @return int
     */
    int getLevel();

    /**
     * Increase the level of the building by one
     */
    void increaseLevel();

    /**
     * Return the upgrade cost of the building based on its type and level.
     * Returns -1 if building is maximum level
     * @return int
     */
    int getUpgradeCost();

    /**
     * Returns the capacity of the building based on its level and type
     * @return int
     */
    int getCapacity();
}
