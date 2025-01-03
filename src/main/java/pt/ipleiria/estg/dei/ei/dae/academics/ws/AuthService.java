package pt.ipleiria.estg.dei.ei.dae.academics.ws;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pt.ipleiria.estg.dei.ei.dae.academics.dtos.AuthDTO;
import pt.ipleiria.estg.dei.ei.dae.academics.ejbs.UserBean;
import pt.ipleiria.estg.dei.ei.dae.academics.security.TokenIssuer;

@Path("auth")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class AuthService {
        @Inject
        private TokenIssuer issuer;
        @EJB
        private UserBean userBean;
        @POST
        @Path("/login")
        public Response authenticate(@Valid AuthDTO auth) {
            if (userBean.canLogin(auth.getUsername(), auth.getPassword())) { String token = issuer.issue(auth.getUsername());
                return Response.ok(token).build();
            }
            return Response.status(Response.Status.UNAUTHORIZED).build(); }
    }