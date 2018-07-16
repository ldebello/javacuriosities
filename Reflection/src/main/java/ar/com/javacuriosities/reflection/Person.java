package ar.com.javacuriosities.reflection;

public class Person {

    private String name;
    private String lastName;
    private Integer age;
    private Boolean isYoung = Boolean.TRUE;

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getYoung() {
        return isYoung;
    }

    public void setYoung(Boolean young) {
        isYoung = young;
    }

    public void talk() {
        System.out.println("My name is: " + name + " and I am talking");
    }

    public void rest(int numberOfHours) {
        printMessage(numberOfHours);
    }

    private void printMessage(int numberOfHours) {
        if (isYoung) {
            System.out.println("My name is: " + name + " and I am going to sleep: " + (numberOfHours / 2) + " hours");
        } else {
            System.out.println("My name is: " + name + " and I am going to sleep: " + numberOfHours + " hours");
        }
    }
}
