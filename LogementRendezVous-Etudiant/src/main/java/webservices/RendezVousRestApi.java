package webservices;

import entities.RendezVous;
import metiers.RendezVousBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/rendezvous")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RendezVousRestApi {

    private static RendezVousBusiness business = new RendezVousBusiness();

    @GET
    public List<RendezVous> getAll() {
        return business.getListeRendezVous();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") int id) {
        RendezVous rdv = business.getRendezVousById(id);
        if (rdv != null) {
            return Response.ok(rdv).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Rendez-vous non trouvé").build();
        }
    }

    @GET
    @Path("/logement/{reference}")
    public List<RendezVous> getByLogement(@PathParam("reference") int reference) {
        return business.getListeRendezVousByLogementReference(reference);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(RendezVous rendezVous) {
        boolean added = business.addRendezVous(rendezVous);
        if (added) {
            return Response.status(Response.Status.CREATED).entity(rendezVous).build(); // ✅ retourne l'objet JSON
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Logement introuvable\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, RendezVous updatedRdv) {
        boolean updated = business.updateRendezVous(id, updatedRdv);
        if (updated) {
            return Response.ok(updatedRdv).build(); // ✅ retourne l’objet JSON
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"RDV ou logement non trouvé\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build(); // ✅ JSON string formatée
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        boolean deleted = business.deleteRendezVous(id);
        if (deleted) {
            return Response.ok("Rendez-vous supprimé avec succès").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Rendez-vous non trouvé").build();
        }
    }
}
