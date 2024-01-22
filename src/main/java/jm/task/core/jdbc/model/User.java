package jm.task.core.jdbc.model;

import jakarta.persistence.Column;;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.Iterator;

@Entity
@Table(name="users")
public class User {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="lastName")
    private String lastName;
    
    @Column(name="age")
    private Byte age;

    public User() {}

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }
    
    @Override
    public String toString() {
        return "User: id = " + id
                + ", name = " + name
                + ", last name = " + lastName
                +  ", age = " + age ;
    }
    
    public static String toString(Collection<User> persons) {
        StringBuilder sb = new StringBuilder();
        sb.append("All Users:");
        
        Iterator<User> it = persons.iterator();
        while (it.hasNext()) {
            sb.append("\n    ");
            sb.append(it.next());
        }
        return sb.toString();
    }
}
