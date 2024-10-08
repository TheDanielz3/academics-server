package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Hibernate;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Subject;

import java.util.List;

@Stateless
public class StudentBean {
    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private CourseBean courseBean;

    @EJB
    private SubjectBean subjectBean;
    public void create (){
        var student = new Student("danielz3","daniel","daniel","daniel@dnaiel.com",courseBean.find(2123L));
        entityManager.persist(student);
    }
    public void create (String username, String password, String name, String email, Long courseCode){
        var student = new Student(username,name,password,email,courseBean.find(courseCode));
        entityManager.persist(student);
    }
    public Student find(String username){
        var student = entityManager.find(Student.class,username);
        if (student == null){
            throw new RuntimeException("course"+username+"not found!");
        }
        return student;
    }
    public List<Student> findAll() {
// remember, maps to: “SELECT s FROM Student s ORDER BY s.name”
        return entityManager.createNamedQuery("getAllStudents", Student.class).getResultList();
    }

    public Student findWithSubjects(String username){
        var student = this.find(username);
        Hibernate.initialize(student.getSubjects());
        return student;
    }

    public void enrollStudentInSubject(String usernameStudent,Long subjectCode){
      Student foundStudent =  this.find(usernameStudent);
      Subject foundSubject = subjectBean.find(subjectCode);
      if(foundStudent == null || foundSubject == null){
          throw new RuntimeException("");
      }
      if(foundStudent.getCourse() == foundStudent.getCourse()){
          foundStudent.addSubject(foundSubject);
          entityManager.persist(foundStudent);
          foundSubject.addStudent(foundStudent);
          entityManager.persist(foundSubject);
      }
      else {
          throw new RuntimeException("Courses are diferent");
      }

    }
}
