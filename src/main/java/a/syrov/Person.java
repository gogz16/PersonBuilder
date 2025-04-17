package a.syrov;

import java.util.OptionalInt;

public class Person {
    protected final String name;
    protected final String surname;
    protected int age = -1; // -1 означает, что возраст неизвестен
    protected String address;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        if (age < 0) {
            throw new IllegalArgumentException("Возраст не может быть отрицательным");
        }
        this.age = age;
    }

    public boolean hasAge() {
        return age != -1;
    }

    public boolean hasAddress() {
        return address != null;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public OptionalInt getAge() {
        return hasAge() ? OptionalInt.of(age) : OptionalInt.empty();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String city) {
        this.address = city;
    }

    public void happyBirthday() {
        if (hasAge()) {
            age++;
        } else {
            throw new IllegalStateException("Возраст неизвестен, нельзя увеличить");
        }
    }

    public PersonBuilder newChildBuilder() {
        return new PersonBuilder()
                .setSurname(this.surname)
                .setAddress(this.address)
                .setAge(0); // возраст ребенка при рождении
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" ").append(surname);
        if (hasAge()) {
            sb.append(", возраст: ").append(age);
        }
        if (hasAddress()) {
            sb.append(", адрес: ").append(address);
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + age;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
