package services;

import org.apache.deltaspike.core.api.future.Futureable;

import java.util.concurrent.Future;

public interface TripPriceGenerator {

    @Futureable
    public Future<Integer> generateTripPrice();
}