package pointandclick.Common;

public class Student {
    public static final Student[] CREDITS_LIST = new Student[] {
        new Student("Leonardo", "Davalos", "000000000"),
        new Student("Matthew",  "Finerty", "111111111"),
        new Student("Jasroop",  "Singh",   "222222222"),
        new Student("Noris",    "Tang",    "333333333"),
        new Student("Vivian",   "Trieu",   "444444444")
    };

    public String firstName;
    public String lastName;
    public String broncoID;

    public Student(String firstName, String lastName, String broncoID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.broncoID = broncoID;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + broncoID + ")";
    }
}
