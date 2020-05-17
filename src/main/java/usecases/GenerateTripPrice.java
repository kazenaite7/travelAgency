package usecases;

import interceptors.LoggedInvocation;
import services.BudgetTripPriceGenerator;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SessionScoped
@Named
public class GenerateTripPrice implements Serializable {
    @Inject
    BudgetTripPriceGenerator budgetTripPriceGenerator;

    private Future<Integer> tripPriceGenerationTask = null;

    @LoggedInvocation
    public String generateNewTripPrice() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        tripPriceGenerationTask = budgetTripPriceGenerator.generateTripPrice();
        return  "/tripDetails.xhtml?faces-redirect=true&tripId=" + requestParameters.get("tripId");
    }

    public boolean isTripPriceGenerationRunning() {
        return tripPriceGenerationTask != null && !tripPriceGenerationTask.isDone();
    }

    public String getTripPriceGenerationStatus() throws ExecutionException, InterruptedException {
        if (tripPriceGenerationTask == null) {
            return null;
        } else if (isTripPriceGenerationRunning()) {
            return "Trip price generation in progress";
        }
        return "Suggested trip price: " + tripPriceGenerationTask.get();
    }
}