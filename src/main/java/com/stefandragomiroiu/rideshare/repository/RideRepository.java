package com.stefandragomiroiu.rideshare.repository;

import com.stefandragomiroiu.rideshare.repository.projection.RideWithDepartureArrivalTimes;
import com.stefandragomiroiu.rideshare.jooq.tables.daos.RideDao;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.stefandragomiroiu.rideshare.jooq.Tables.RIDE;
import static com.stefandragomiroiu.rideshare.jooq.Tables.RIDE_CONNECTION;

@Repository
public class RideRepository extends RideDao {

    private final DSLContext ctx;

    public RideRepository(DSLContext ctx) {
        super(ctx.configuration());
        this.ctx = ctx;
    }

    /**
     * Find all rides that have the input departure location and arrival location and goes in the direction from departure to arrival.
     */
    public List<RideWithDepartureArrivalTimes> findBy(long departureLocation, long arrivalLocation, LocalDate date) {
        var RC_DEP = RIDE_CONNECTION.as("rc_dep");
        var RC_ARR = RIDE_CONNECTION.as("rc_arr");
        return ctx.select(
                        RIDE.RIDE_ID,
                        RIDE.DRIVER,
                        RC_DEP.DEPARTURE_TIME,
                        RC_ARR.ARRIVAL_TIME
                )
                .from(RIDE)
                .innerJoin(RC_DEP).on(RC_DEP.RIDE_ID.eq(RIDE.RIDE_ID))
                .innerJoin(RC_ARR).on(RC_ARR.RIDE_ID.eq(RIDE.RIDE_ID))
                .where(RC_DEP.DEPARTURE_LOCATION.eq(departureLocation))
                .and(RC_ARR.ARRIVAL_LOCATION.eq(arrivalLocation))
                .and(RC_DEP.DEPARTURE_TIME.lessThan(RC_ARR.ARRIVAL_TIME))
                .and(RC_DEP.DEPARTURE_TIME.cast(LocalDate.class).eq(date))
                .fetchInto(RideWithDepartureArrivalTimes.class);
    }


}
