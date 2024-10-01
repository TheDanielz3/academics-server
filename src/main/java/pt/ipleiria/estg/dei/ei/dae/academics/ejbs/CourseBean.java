package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;

import java.util.List;

@Stateless
public class CourseBean {
    @PersistenceContext
    private EntityManager entityManager;

    public void create (){
        var course = new Course(2123L,"EI");
        entityManager.persist(course);
        course = new Course(1L,"Mecanica");
        entityManager.persist(course);

    }
    public void create (long code,String name){
        var course = new Course(code,name);
        entityManager.persist(course);

    }
    public List<Course> findAll() {
        // remember, maps to: “SELECT c FROM Courses c ORDER BY c.code”
        return entityManager.createNamedQuery("getAllCourses", Course.class).getResultList();
    }
    public Course find(long code){
        var course = entityManager.find(Course.class,code);
        if (course == null){
            throw new RuntimeException("course"+code+"not found!");
        }
        return course;
    }
}
