package pt.ipleiria.estg.dei.ei.dae.academics.ws;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pt.ipleiria.estg.dei.ei.dae.academics.dtos.CourseDTO;
import pt.ipleiria.estg.dei.ei.dae.academics.dtos.StudentDTO;
import pt.ipleiria.estg.dei.ei.dae.academics.ejbs.CourseBean;
import pt.ipleiria.estg.dei.ei.dae.academics.ejbs.StudentBean;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;

import java.util.List;

@Path("courses")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class CourseService {
    @EJB
    private CourseBean courseBean;


    @GET// means: to call this endpoint, we need to use the HTTP GET method
    @Path("/all") // means: the relative url path is “/api/students/all”
    public List<CourseDTO> getAllCourses() {
        return CourseDTO.from(courseBean.findAll());
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewCouse (CourseDTO courseDTO){
        courseBean.create(
                courseDTO.getCode(),
                courseDTO.getName()
        );
        Course newCourse = courseBean.find(courseDTO.getCode());
        return Response.status(Response.Status.CREATED) .entity(CourseDTO.from(newCourse)).build();
    }

}
