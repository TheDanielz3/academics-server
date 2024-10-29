package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityNotFoundException;

import java.util.logging.Logger;

@Startup
@Singleton
public class ConfigBean {
    @EJB
    private StudentBean studentBean;
    @EJB
    private CourseBean courseBean;

    @EJB
    private SubjectBean subjectBean;

    @EJB
    private TeacherBean teacherBean;

    @EJB
    private AdministratorBean administratorBean;

    private static final Logger logger = Logger.getLogger("ejbs.ConfigBean");

    @PostConstruct
    public void populateDB() throws MyEntityNotFoundException {
        System.out.println("Hello Java EE!");
        courseBean.create();
        subjectBean.create();
        studentBean.create();
        studentBean.enrollStudentInSubject("danielz3",23L);

    /*    try {
            studentBean.create("foo", "bar", "foo", "foo@bar.com", 9119L);
            studentBean.create("foo2", "bar", "foo2", "foo2@bar.com", 1000L);
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }*/

        teacherBean.create();
        administratorBean.create();


    }
}