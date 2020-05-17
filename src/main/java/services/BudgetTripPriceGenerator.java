package services;

import org.apache.deltaspike.core.api.future.Futureable;

import javax.ejb.AsyncResult;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.io.Serializable;
import java.util.concurrent.Future;
import java.util.Random;

@ApplicationScoped
@Alternative
public class BudgetTripPriceGenerator implements Serializable, TripPriceGenerator {

    @Override
    @Futureable
    public Future<Integer> generateTripPrice() {
        try {
            Thread.sleep(3000); // Simulate intensive work
        } catch (InterruptedException e) {
        }
        final Integer generatedTripPrice = new Random().nextInt(200);
        return new AsyncResult<>(generatedTripPrice);
    }
}