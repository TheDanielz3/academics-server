package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Administrator;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;
import pt.ipleiria.estg.dei.ei.dae.academics.security.Hasher;

import java.util.List;

@Stateless
public class AdministratorBean {
    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Hasher hasher;

    public void create (){
        var administrator = new Administrator("JulioDosOculos", hasher.hash("lalala"),"Julinho","julios_dos_oculos@julinho.com");
        entityManager.persist(administrator);

    }
    public void create (String username, String password, String name, String email, Long courseCode){
        var administrator = new Administrator(username,name,hasher.hash(password),email);
        entityManager.persist(administrator);
    }
    /*public List<Course> findAll() {
        // remember, maps to: “SELECT c FROM Courses c ORDER BY c.code”
        return entityManager.createNamedQuery("getAllCourses", Course.class).getResultList();
    }
    public Course find(long code){
        var course = entityManager.find(Course.class,code);
        if (course == null){
            throw new RuntimeException("course"+code+"not found!");
        }
        return course;
    }*/
}
