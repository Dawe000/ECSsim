import university.Staff;
import university.University;

import java.util.ArrayList;

public class EcsSim {
    University university;
    ArrayList<Staff> staffMarket;

    EcsSim(int funding, ArrayList<Staff> staff){
        university = new University(funding);
        staffMarket = staff;
    }
}
