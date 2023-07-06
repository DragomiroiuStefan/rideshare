package com.stefandragomiroiu.rideshare.repository;

import com.stefandragomiroiu.rideshare.repository.dto.DriverRating;
import com.stefandragomiroiu.rideshare.tables.daos.UsersDao;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static com.stefandragomiroiu.rideshare.Tables.*;
import static org.jooq.impl.DSL.avg;

@Repository
public class UserRepository extends UsersDao {

    private final DSLContext ctx;


    public UserRepository(DSLContext ctx) {
        super(ctx.configuration());
        this.ctx = ctx;
    }

    /**
     * Find the number of ratings and average rating for a driver
     */
    public DriverRating findAverageRatingBy(Long userId) {
        return ctx.select(
                        avg(RIDE_RATINGS.RATING),
                        org.jooq.impl.DSL.count(RIDE_RATINGS.RATING)
                )
                .from(USERS
                        .innerJoin(RIDES).on(RIDES.DRIVER.eq(USERS.USER_ID))
                        .innerJoin(RIDE_RATINGS).on(RIDE_RATINGS.RIDE_ID.eq(RIDES.RIDE_ID)))
                .where(USERS.USER_ID.eq(userId))
                .fetchOneInto(DriverRating.class);

    }
}
