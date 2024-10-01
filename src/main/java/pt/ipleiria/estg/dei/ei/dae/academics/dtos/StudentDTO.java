package pt.ipleiria.estg.dei.ei.dae.academics.dtos;

import jakarta.persistence.Id;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class StudentDTO implements Serializable {
    @Id
    private String username;
    private String name;
    private String password;
    private String email;

    private Long courseCode;
    private String courseName;

    public StudentDTO() {
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(Long courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public StudentDTO(String username, String name, String password, String email,Long courseCode,String courseName) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.email = email;
        this.courseCode = courseCode;
        this.courseName = courseName;
    }
    public static StudentDTO from(Student student) { return new StudentDTO(
            student.getUsername(),
            student.getPassword(),
            student.getName(),
            student.getEmail(),
            student.getCourse().getCode(),
            student.getCourse().getName()
    ); }
    // converts an entire list of entities into a list of DTOs
    public static List<StudentDTO> from(List<Student> students) {
        return students.stream().map(StudentDTO::from).collect(Collectors.toList());
    }
}
