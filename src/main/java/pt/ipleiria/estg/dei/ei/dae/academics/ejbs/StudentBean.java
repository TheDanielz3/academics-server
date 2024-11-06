package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Hibernate;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Subject;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityNotFoundException;
import pt.ipleiria.estg.dei.ei.dae.academics.security.Hasher;

import java.util.List;

@Stateless
public class StudentBean {
    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private CourseBean courseBean;

    @Inject
    private Hasher hasher;

    @EJB
    private SubjectBean subjectBean;
    public void create (){
        var student = new Student("danielz3",hasher.hash("daniel"),"daniel","daniel@dnaiel.com",courseBean.find(2123L));
        entityManager.persist(student);
    }
    public void create (String username, String password, String name, String email, Long courseCode) throws MyEntityExistsException {
        var found = entityManager.find(Student.class,username);

        if (found == null)
        {
            var student = new Student(username,name,hasher.hash(password),email,courseBean.find(courseCode));
            entityManager.persist(student);
        }
        else {
        throw new MyEntityExistsException("Student with username '" + username + "' already exists");
        }
    }
    public Student find(String username)throws MyEntityNotFoundException{
        var student = entityManager.find(Student.class,username);
        if (student == null){
            throw new MyEntityNotFoundException("course"+username+"not found!");
        }
        return student;
    }
    public List<Student> findAll() {
// remember, maps to: “SELECT s FROM Student s ORDER BY s.name”
        return entityManager.createNamedQuery("getAllStudents", Student.class).getResultList();
    }

    public Student findWithSubjects(String username)throws MyEntityNotFoundException{
        var student = this.find(username);
        Hibernate.initialize(student.getSubjects());
        return student;
    }

    public void enrollStudentInSubject(String usernameStudent,Long subjectCode)throws MyEntityNotFoundException{
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
