package pt.ipleiria.estg.dei.ei.dae.academics.dtos;

import jakarta.persistence.Id;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class CourseDTO implements Serializable {
    @Id
    private long code;
    private String name;

    public CourseDTO() {
    }

    public CourseDTO(long code, String name) {
        this.code = code;
        this.name = name;

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

    public static CourseDTO from(Course course) {
        return new CourseDTO(
            course.getCode(),
            course.getName()
    ); }
    // converts an entire list of entities into a list of DTOs
    public static List<CourseDTO> from(List<Course> courses) {
        return courses.stream().map(CourseDTO::from).collect(Collectors.toList());
    }
}
