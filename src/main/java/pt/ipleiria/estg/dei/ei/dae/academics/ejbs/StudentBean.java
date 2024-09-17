package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;

@Stateless
public class StudentBean {
    @PersistenceContext
    private EntityManager entityManager;

    public void create (){
        var student = new Student("danielz3","daniel","daniel","daniel@dnaiel.com");
        entityManager.persist(student);
    }
}
