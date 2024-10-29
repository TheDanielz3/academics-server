package pt.ipleiria.estg.dei.ei.dae.academics.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllCourses",
                query = "SELECT c FROM Course c ORDER BY c.code"
        )
})
public class Course extends Versionable implements Serializable {
    @Id
    private Long code;

    private String name;

    @OneToMany(mappedBy = "course")
    private List<Student> students;

    @OneToMany(mappedBy = "course")
    private List<Subject> subjects;


    public Course() {
    }

    public Course(Long code, String name) {
        this.code = code;
        this.name = name;
        this.students = new ArrayList<>();
        this.subjects = new ArrayList<>();
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

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
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

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Course course = (Course) obj;
        return Objects.equals(code,course.code);
    }

    public void addSubject(Subject subject){
        this.subjects.add(subject);
    }
    public void removeSubject(Subject subject){
        boolean foundSubject =this.subjects.contains(subject);
        if (foundSubject)
            this.subjects.remove(subject);
    }
}
