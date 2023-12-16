package university;

public class Staff {
    public String name;
    public int skill;
    public int yearsOfTeaching;
    public int stamina;

    public Staff(String name, int skill){
        this.name = name;
        this.skill = skill;
        yearsOfTeaching = 0;
        stamina = 100;
    }

    public int instruct(int numberOfStudents){
        if (skill < 100) skill ++;
        stamina = (int) (stamina - java.lang.Math.ceil(numberOfStudents/(20+skill))*20);
        if (stamina<0) stamina=0;
        return (100*skill)/(100+numberOfStudents);
    }

    public void replenishStamina(){
        stamina+=20;
        if (stamina > 100) stamina = 100;
    }
    void increaseYearsOfTeaching(){
        yearsOfTeaching++;
    }

}
