package services;

import org.apache.deltaspike.core.api.future.Futureable;

import javax.ejb.AsyncResult;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.Future;

@ApplicationScoped
public class PriceyTripPriceGenerator implements Serializable, TripPriceGenerator {

    @Override
    @Futureable
    public Future<Integer> generateTripPrice() {
        try {
            Thread.sleep(3000); // Simulate intensive work
        } catch (InterruptedException e) {
        }
        final Integer generatedTripPrice = new Random().nextInt(300) + 200;
        return new AsyncResult<>(generatedTripPrice);
    }
}