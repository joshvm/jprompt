package example;

import jvm.prompt.Prompt;
import jvm.prompt.Prompts;
import jvm.prompt.impl.basic.IntegerPrompt;
import jvm.prompt.impl.basic.StringPrompt;
import jvm.prompt.impl.object.ReflectiveObjectPrompt;

import java.util.Scanner;

public class Examples {

    public void testMultiplePrompts(){
        final Prompt<String> namePrompt = new StringPrompt("name", "Enter your name")
                .rule(s -> s.length() > 1, "Name must be at least 2 characters long")
                .rule(s -> s.length() < 20, "Name must be less than 20 characters")
                .rule(s -> s.chars().allMatch(Character::isLetter), "Name can only contain letters");
        final Prompt<Integer> agePrompt = new IntegerPrompt("age", "Enter your age")
                .rule(i -> i >= 0, "You can't be %s years old", Prompt.VALUE_PLACEHOLDER)
                .rule(i -> i > 10, "You must be at least 11 years old")
                .rule(i -> i % 2 == 0, "Your age must be an even number");
        try(final Scanner input = new Scanner(System.in)){
            final String name = namePrompt.prompt(input);
            final int age = agePrompt.prompt(input);
            System.out.println("name: " + name);
            System.out.println("age: " + age);
        }
    }

    public void testReflective(){
        final Prompt<String> namePrompt = new StringPrompt("name", "Enter your name")
                .rule(s -> s.length() > 1, "Name must be at least 2 characters long")
                .rule(s -> s.length() < 20, "Name must be less than 20 characters")
                .rule(s -> s.chars().allMatch(Character::isLetter), "Name can only contain letters");
        final Prompt<Integer> agePrompt = new IntegerPrompt("age", "Enter your age")
                .rule(i -> i >= 0, "You can't be %s years old", Prompt.VALUE_PLACEHOLDER)
                .rule(i -> i > 10, "You must be at least 11 years old")
                .rule(i -> i % 2 == 0, "Your age must be an even number");
        final Prompts prompts = Prompts.of(namePrompt, agePrompt);
        final Prompt<Person> personPrompt = new ReflectiveObjectPrompt<>(Person.class, "person", prompts);
        try(final Scanner input = new Scanner(System.in)){
            final Person person = personPrompt.prompt(input);
            System.out.println(person);
        }
    }
}
