package services;

import org.apache.deltaspike.core.api.future.Futureable;

import javax.ejb.AsyncResult;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.Future;

@ApplicationScoped
@Specializes
public class VeryPriceyTripPriceGenerator extends  PriceyTripPriceGenerator implements Serializable {

    @Override
    @Futureable
    public Future<Integer> generateTripPrice() {
        try {
            Thread.sleep(3000); // Simulate intensive work
        } catch (InterruptedException e) {
        }
        final Integer generatedTripPrice = new Random().nextInt(300) + 400;
        return new AsyncResult<>(generatedTripPrice);
    }
}