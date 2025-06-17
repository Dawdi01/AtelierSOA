package webservices;

import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/logement")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LogementRestApi {

    private static final LogementBusiness business = new LogementBusiness();

    @GET
    public List<Logement> getAll() {
        return business.getLogements();
    }

    @GET
    @Path("/{reference}")
    public Response getByReference(@PathParam("reference") int reference) {
        Logement logement = business.getLogementsByReference(reference);
        if (logement != null) {
            return Response.ok(logement).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Logement non trouvé avec la référence : " + reference)
                    .build();
        }
    }

    @GET
    @Path("/delegation/{delegation}")
    public List<Logement> getByDelegation(@PathParam("delegation") String delegation) {
        return business.getLogementsByDelegation(delegation);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Logement logement) {
        boolean added = business.addLogement(logement);
        if (added) {
            return Response.status(Response.Status.CREATED).entity(logement).build(); // JSON attendu
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"message\": \"Erreur : Logement non ajouté\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @PUT
    @Path("/{reference}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("reference") int reference, Logement logement) {
        boolean updated = business.updateLogement(reference, logement);
        if (updated) {
            return Response.ok(logement).build(); // ✅ retourne du JSON
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"message\": \"Erreur : logement non trouvé\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @DELETE
    @Path("/{reference}")
    public Response delete(@PathParam("reference") int reference) {
        boolean deleted = business.deleteLogement(reference);
        if (deleted) {
            return Response.ok("Logement supprimé avec succès").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Logement non trouvé pour suppression")
                    .build();
        }
    }
}
