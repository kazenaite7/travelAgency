package rest.contracts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TripDto {

    private String Name;

    private Integer TripPrice;

    private String GuideName;
}
