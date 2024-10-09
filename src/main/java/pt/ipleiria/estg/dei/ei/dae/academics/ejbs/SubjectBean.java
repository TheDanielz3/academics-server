package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Subject;

import java.util.List;

@Stateless
public class SubjectBean {
    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private CourseBean courseBean;
    public void create (){
        var subject = new Subject(23L,"DAE","2024/2025",2024,courseBean.find(2123L));
        entityManager.persist(subject);


    }
    public void create (long code,String name,String schoolYear,Integer courseYear,Long courseCode){
        var subject = new Subject(code,name,schoolYear,courseYear,courseBean.find(courseCode));
        entityManager.persist(subject);

    }
    public List<Subject> findAll() {
        // remember, maps to: “SELECT c FROM Courses c ORDER BY c.code”
        return entityManager.createNamedQuery("getAllSubjects", Subject.class).getResultList();
    }
    public Subject find(Long code){
        var subject = entityManager.find(Subject.class,code);
        if (subject == null){
            throw new RuntimeException("subject"+code+"not found!");
        }
        return subject;
    }
}
