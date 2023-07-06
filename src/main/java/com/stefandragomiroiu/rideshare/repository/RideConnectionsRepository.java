package com.stefandragomiroiu.rideshare.repository;

import com.stefandragomiroiu.rideshare.repository.dto.RideConnectionWithAvailableSeats;
import com.stefandragomiroiu.rideshare.tables.daos.RideConnectionsDao;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.stefandragomiroiu.rideshare.Tables.BOOKING_CONNECTIONS;
import static com.stefandragomiroiu.rideshare.Tables.RIDE_CONNECTIONS;
import static org.jooq.impl.DSL.val;

@Repository
public class RideConnectionsRepository extends RideConnectionsDao {

    private final DSLContext ctx;


    public RideConnectionsRepository(DSLContext ctx) {
        super(ctx.configuration());
        this.ctx = ctx;
    }

    /**
     * Find all ride connections between departureTime and arrivalTime and return the price and available seats for each one.
     * Available seats = ride seats - bookings made on that connection.
     */
    public List<RideConnectionWithAvailableSeats> findBy(LocalDateTime departureTime, LocalDateTime arrivalTime, Long rideId) {
        return ctx.select(
                        RIDE_CONNECTIONS.CONNECTION_ID,
                        RIDE_CONNECTIONS.DEPARTURE_TIME,
                        RIDE_CONNECTIONS.ARRIVAL_TIME,
                        RIDE_CONNECTIONS.DEPARTURE_ADDRESS,
                        RIDE_CONNECTIONS.ARRIVAL_ADDRESS,
                        RIDE_CONNECTIONS.PRICE,
                        val(rideId).minus(org.jooq.impl.DSL.count(BOOKING_CONNECTIONS.BOOKING_ID)).as("availableSeats")
                        )
                .from(RIDE_CONNECTIONS
                        .leftJoin(BOOKING_CONNECTIONS).on(BOOKING_CONNECTIONS.CONNECTION_ID.eq(RIDE_CONNECTIONS.CONNECTION_ID)))
                .where(RIDE_CONNECTIONS.RIDE_ID.eq(rideId))
                .and(RIDE_CONNECTIONS.DEPARTURE_TIME.greaterOrEqual(departureTime))
                .and(RIDE_CONNECTIONS.ARRIVAL_TIME.lessOrEqual(arrivalTime))
                .groupBy(RIDE_CONNECTIONS.CONNECTION_ID)
                .fetchInto(RideConnectionWithAvailableSeats.class);
    }

}
