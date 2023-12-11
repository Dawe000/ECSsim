package facilities.buildings;

import facilities.Facility;

public abstract class Resource extends Facility implements Building{

    int level;

    Resource(String name){
        super(name);
        level = 1;
    }

        public void increaseLevel(){
        level++;
    }
    public int getLevel(){
        return level;
    }

}
