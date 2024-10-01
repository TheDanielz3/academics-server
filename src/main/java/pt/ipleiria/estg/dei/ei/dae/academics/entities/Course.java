package pt.ipleiria.estg.dei.ei.dae.academics.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllCourses",
                query = "SELECT c FROM Course c ORDER BY c.code"
        )
})
public class Course implements Serializable {
    @Id
    private Long code;

    private String name;

    @OneToMany(mappedBy = "course")
    private List<Student> students;

    public Course() {
    }

    public Course(Long code, String name) {
        this.code = code;
        this.name = name;
        this.students = new ArrayList<>();
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
    public void addStudent(Student student){
        this.students.add(student);
    }
    public void removeStudent(Student student){
        boolean foundStudent =this.students.contains(student);
        if (foundStudent)
            this.students.remove(student);
    }
}
