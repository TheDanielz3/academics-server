package pt.ipleiria.estg.dei.ei.dae.academics.dtos;

import jakarta.persistence.Id;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Subject;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class SubjectDTO implements Serializable {
    @Id
    private Long code;
    private String name;
    private Long courseCode;
    private String courseName;
    private Integer courseYear;
    private String scholarYear;

    public SubjectDTO() {
    }

    public SubjectDTO(long code, String name, Long courseCode, String courseName, Integer courseYear, String scholarYear) {
        this.code = code;
        this.name = name;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseYear = courseYear;
        this.scholarYear = scholarYear;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(Integer courseYear) {
        this.courseYear = courseYear;
    }

    public String getScholarYear() {
        return scholarYear;
    }

    public void setScholarYear(String scholarYear) {
        this.scholarYear = scholarYear;
    }

    public static SubjectDTO from(Subject subject) {
        return new SubjectDTO(
                subject.getCode(),
                subject.getName(),
                subject.getCourse().getCode(),
                subject.getCourse().getName(),
                subject.getCourseYear(),
                subject.getSchoolYear()

        ); }
    // converts an entire list of entities into a list of DTOs
    public static List<SubjectDTO> from(List<Subject> subjects) {
        return subjects.stream().map(SubjectDTO::from).collect(Collectors.toList());
    }
}
