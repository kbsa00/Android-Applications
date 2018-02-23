package Model;

/**
 * Created by Eier on 2/8/2018.
 */

public class Person {
    private int personAge;
    private String personName;
    private int persId;
    private String persAdress;


    public Person(int personAge, String personName, int persId) {
        this.personAge = personAge;
        this.personName = personName;
        this.persId = persId;
    }

    public Person(){

    }

    public String getPersAdress() {return persAdress;}

    public void setPersAdress(String persAdress) {this.persAdress = persAdress;}

    public int getPersonAge() {
        return personAge;
    }

    public void setPersonAge(int personAge) {
        this.personAge = personAge;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public int getPersId() {
        return persId;
    }

    public void setPersId(int id) {
        this.persId = id;
    }
}
