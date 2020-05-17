package rest;

import entities.Trip;
import lombok.Getter;
import lombok.Setter;
import persistence.TripsDAO;
import rest.contracts.TripDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/trips")
public class TripsController {

    @Inject
    @Setter
    @Getter
    private TripsDAO tripsDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Trip trip = tripsDAO.findOne(id);
        if (trip == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        TripDto tripDto = new TripDto();
        tripDto.setName(trip.getName());
        tripDto.setTripPrice(trip.getTripPrice());
        tripDto.setGuideName(trip.getGuide().getName());

        return Response.ok(tripDto).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer tripId, TripDto tripData) {
        try {
            Trip existingTrip = tripsDAO.findOne(tripId);
            if (existingTrip == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingTrip.setName(tripData.getName());
            existingTrip.setTripPrice(tripData.getTripPrice());
            tripsDAO.update(existingTrip);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
