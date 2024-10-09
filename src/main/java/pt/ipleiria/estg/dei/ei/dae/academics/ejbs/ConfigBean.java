package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
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
    @PostConstruct
    public void populateDB() {
        System.out.println("Hello Java EE!");
        courseBean.create();
        subjectBean.create();
        studentBean.create();
        studentBean.enrollStudentInSubject("danielz3",23L);
        teacherBean.create();
        administratorBean.create();


    }
}