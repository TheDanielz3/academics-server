package pt.ipleiria.estg.dei.ei.dae.academics.ws;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pt.ipleiria.estg.dei.ei.dae.academics.dtos.CourseDTO;
import pt.ipleiria.estg.dei.ei.dae.academics.ejbs.CourseBean;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;

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

    @GET// means: to call this endpoint, we need to use the HTTP GET method
    @Path("/{id}") // means: the relative url path is “/api/students/all”
    public CourseDTO viewCourse(@PathParam("id") long courseiD) {
       Course course = courseBean.find(courseiD);

        return CourseDTO.from(course);
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewCourse (CourseDTO courseDTO){
        courseBean.create(
                courseDTO.getCode(),
                courseDTO.getName()
        );
        Course newCourse = courseBean.find(courseDTO.getCode());
        return Response.status(Response.Status.CREATED).entity(CourseDTO.from(newCourse)).build();
    }


    @PUT
    @Path("/")
    public Response editCurse (CourseDTO courseDTO){
        if (courseDTO == null ){
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        Course res = courseBean.update(courseDTO);
        if(res != null){
            return Response.status(Response.Status.OK).entity(CourseDTO.from(res)).build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }


    @DELETE
    @Path("/{id}")
    public Response removeCourse (@PathParam("id") Long courseiD){
        if (courseiD == null ){
            return Response.status(Response.Status.NO_CONTENT).build();
        }
       Boolean res = courseBean.delete(courseiD);
        if(res){
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

}
