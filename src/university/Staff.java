package university;

/**
 * This class represents a staff member
 */
public class Staff {
    public String name;
    public int skill;
    public int yearsOfTeaching;
    public int stamina;

    /**
     * Constructor for Staff
     * @param name String: name of new Staff
     * @param skill int: skill of the new Staff between 1 and 100
     */
    public Staff(String name, int skill){
        this.name = name;
        this.skill = skill;
        yearsOfTeaching = 0;
        stamina = 100;
    }

    /**
     * Instructs students, reduces stamina, increases skill and returns reputation earned
     * @param numberOfStudents int: number of students being instructed
     * @return int: reputation earned
     */
    public int instruct(int numberOfStudents){
        if (skill < 100) skill ++;
        stamina = (int) (stamina - (double) (numberOfStudents / (20 + skill)) *20);
        if (stamina<0) stamina=0;
        return (100*skill)/(100+numberOfStudents);
    }

    /**
     * Increases the stamina of the staff member by 20
     */
    public void replenishStamina(){
        stamina+=20;
        if (stamina > 100) stamina = 100;
    }

    /**
     * Increments the Staff members yearsOfTeaching
     */
    public void increaseYearsOfTeaching(){
        yearsOfTeaching++;
    }

}
