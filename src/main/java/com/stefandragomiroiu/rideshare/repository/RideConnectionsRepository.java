package com.stefandragomiroiu.rideshare.repository;

import com.stefandragomiroiu.rideshare.repository.projection.RideConnectionWithAvailableSeatsAndPrice;
import com.stefandragomiroiu.rideshare.jooq.tables.daos.RideConnectionDao;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.stefandragomiroiu.rideshare.jooq.Tables.BOOKING_CONNECTION;
import static com.stefandragomiroiu.rideshare.jooq.Tables.RIDE_CONNECTION;
import static org.jooq.impl.DSL.val;

@Repository
public class RideConnectionsRepository extends RideConnectionDao {

    private final DSLContext ctx;


    public RideConnectionsRepository(DSLContext ctx) {
        super(ctx.configuration());
        this.ctx = ctx;
    }

    /**
     * Find all ride connections between departureTime and arrivalTime and return the price and available seats for each one.
     * Available seats = ride seats - bookings made on that connection.
     */
    public List<RideConnectionWithAvailableSeatsAndPrice> findBy(LocalDateTime departureTime, LocalDateTime arrivalTime, Long rideId) {
        return ctx.select(
                        RIDE_CONNECTION.CONNECTION_ID,
                        RIDE_CONNECTION.DEPARTURE_TIME,
                        RIDE_CONNECTION.ARRIVAL_TIME,
                        RIDE_CONNECTION.DEPARTURE_ADDRESS,
                        RIDE_CONNECTION.ARRIVAL_ADDRESS,
                        RIDE_CONNECTION.PRICE,
                        val(rideId).minus(org.jooq.impl.DSL.count(BOOKING_CONNECTION.BOOKING_ID)).as("availableSeats")
                        )
                .from(RIDE_CONNECTION
                        .leftJoin(BOOKING_CONNECTION).on(BOOKING_CONNECTION.CONNECTION_ID.eq(RIDE_CONNECTION.CONNECTION_ID)))
                .where(RIDE_CONNECTION.RIDE_ID.eq(rideId))
                .and(RIDE_CONNECTION.DEPARTURE_TIME.greaterOrEqual(departureTime))
                .and(RIDE_CONNECTION.ARRIVAL_TIME.lessOrEqual(arrivalTime))
                .groupBy(RIDE_CONNECTION.CONNECTION_ID)
                .fetchInto(RideConnectionWithAvailableSeatsAndPrice.class);
    }

}
