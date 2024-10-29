package pt.ipleiria.estg.dei.ei.dae.academics.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table(
        name = "subjects", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "course_code", "school_year"}) }
)
@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllSubjects",
                query = "SELECT s FROM Subject s ORDER BY s.course.name,s.schoolYear DESC ,s.courseYear,s.name"
        )
})public class Subject extends Versionable implements Serializable {
    @Id
    private Long code;

    private String name;

    @Column(name = "school_year")
    private String schoolYear;

    @Column(name = "course_year")
    private Integer courseYear;

    @ManyToOne
    private Course course;

    @JoinTable(
            name = "subject_student",
            joinColumns = @JoinColumn(
                    name = "subject_code",
                    referencedColumnName = "code" ),
            inverseJoinColumns = @JoinColumn( name = "student_username", referencedColumnName = "username"
            ) )
    @ManyToMany
    private List<Student> students;


    @JoinTable(
            name = "subject_teacher",
            joinColumns = @JoinColumn(
                    name = "subject_code",
                    referencedColumnName = "code" ),
            inverseJoinColumns = @JoinColumn( name = "teacher_username", referencedColumnName = "username"
            ) )
    @ManyToMany
    private List<Teacher> teachers;

    public Subject() {
    }

    public Subject(Long code, String name, String schoolYear, Integer courseYear, Course course) {
        this.code = code;
        this.name = name;
        this.schoolYear = schoolYear;
        this.courseYear = courseYear;
        this.course = course;
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
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

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Integer getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(Integer courseYear) {
        this.courseYear = courseYear;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public void addStudent(Student student){
        this.students.add(student);
    }
    public void removeStudent(Student student){
        boolean foundStudent =this.students.contains(student);
        if (foundStudent)
            this.students.remove(student);
    }


    public void addTeacher(Teacher teacher){
        this.teachers.add(teacher);
    }
    public void removeTeacher(Teacher teacher){
        boolean foundTeacher =this.teachers.contains(teacher);
        if (foundTeacher)
            this.students.remove(teacher);
    }
}
