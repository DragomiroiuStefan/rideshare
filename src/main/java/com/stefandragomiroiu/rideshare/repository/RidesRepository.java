package com.stefandragomiroiu.rideshare.repository;

import com.stefandragomiroiu.rideshare.repository.dto.RideWithDepartureArrivalTimes;
import com.stefandragomiroiu.rideshare.tables.daos.RidesDao;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.stefandragomiroiu.rideshare.Tables.RIDES;
import static com.stefandragomiroiu.rideshare.Tables.RIDE_CONNECTIONS;

@Repository
public class RidesRepository extends RidesDao {

    private final DSLContext ctx;

    public RidesRepository(DSLContext ctx) {
        super(ctx.configuration());
        this.ctx = ctx;
    }

    /**
     * Find all rides that have the input departure location and arrival location and goes in the direction from departure to arrival.
     */
    public List<RideWithDepartureArrivalTimes> findBy(long departureLocation, long arrivalLocation, LocalDate date) {
        var RC_DEP = RIDE_CONNECTIONS.as("rc_dep");
        var RC_ARR = RIDE_CONNECTIONS.as("rc_arr");
        return ctx.select(
                        RIDES.RIDE_ID,
                        RIDES.DRIVER,
                        RC_DEP.DEPARTURE_TIME,
                        RC_ARR.ARRIVAL_TIME
                )
                .from(RIDES)
                .innerJoin(RC_DEP).on(RC_DEP.RIDE_ID.eq(RIDES.RIDE_ID))
                .innerJoin(RC_ARR).on(RC_ARR.RIDE_ID.eq(RIDES.RIDE_ID))
                .where(RC_DEP.DEPARTURE_LOCATION.eq(departureLocation))
                .and(RC_ARR.ARRIVAL_LOCATION.eq(arrivalLocation))
                .and(RC_DEP.DEPARTURE_TIME.lessThan(RC_ARR.ARRIVAL_TIME))
                .and(RC_DEP.DEPARTURE_TIME.cast(LocalDate.class).eq(date))
                .fetchInto(RideWithDepartureArrivalTimes.class);
    }
}
