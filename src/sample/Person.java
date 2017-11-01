package sample;

public class Person {
    private String name, surName;

    public Person(String name, String surName) {
     set(name, surName);
    }
    public void set(String name, String surName){
        this.name = name;
        this.surName = surName;

    }

    public Person() {
        this("", "");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    @Override
    public String toString() {
        return name + " " + surName;
    }
}
