package example;

public class Person {

    private final String name;
    private final int age;

    public Person(final String name, final int age){
        this.name = name;
        this.age = age;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public String toString(){
        return String.format("Person[name=%s, age=%d]", name, age);
    }
}