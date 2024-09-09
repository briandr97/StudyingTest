package stream;

import java.util.Objects;

public class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Person other)) {
            return false;
        }
        if (other.getAge() != age) {
            return false;
        }
        if (!Objects.equals(other.getName(), name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Person(").append("name=").append(name).append(", ").append("age=").append(age);
        return sb.toString();
    }
}
