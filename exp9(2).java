// Student.java
  import jakarta.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;

    public Student() {}
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters & setters
}
//StudentDAO.java
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class StudentDAO {

    private static SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public void create(Student student) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(student);
        tx.commit();
        session.close();
    }

    public Student read(int id) {
        Session session = factory.openSession();
        Student student = session.get(Student.class, id);
        session.close();
        return student;
    }

    public void update(Student student) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(student);
        tx.commit();
        session.close();
    }

    public void delete(Student student) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(student);
        tx.commit();
        session.close();
    }
}
//Main.javapublic class Main {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();
        Student s = new Student("John", 20);
        dao.create(s);

        Student fetched = dao.read(s.getId());
        System.out.println("Read: " + fetched.getName());

        fetched.setAge(21);
        dao.update(fetched);

        dao.delete(fetched);
    }
}
