package com.stefandragomiroiu.rideshare.repository;

import com.stefandragomiroiu.rideshare.jooq.tables.pojos.User;
import com.stefandragomiroiu.rideshare.repository.dto.DriverRating;
import com.stefandragomiroiu.rideshare.jooq.tables.daos.UserDao;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static com.stefandragomiroiu.rideshare.jooq.Tables.*;
import static org.jooq.impl.DSL.avg;

@Repository
public class UserRepository extends UserDao {

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
                        avg(RIDE_RATING.RATING),
                        org.jooq.impl.DSL.count(RIDE_RATING.RATING)
                )
                .from(USER
                        .innerJoin(RIDE).on(RIDE.DRIVER.eq(USER.USER_ID))
                        .innerJoin(RIDE_RATING).on(RIDE_RATING.RIDE_ID.eq(RIDE.RIDE_ID)))
                .where(USER.USER_ID.eq(userId))
                .fetchOneInto(DriverRating.class);

    }

    @Transactional
    public void setProfilePicture(Long userId, String profilePicture) {
        ctx.update(USER)
                .set(USER.PROFILE_PICTURE, profilePicture)
                .where(USER.USER_ID.eq(userId))
                .execute();
    }

    @Transactional
    public void updateProfileInfo(User user) {
        ctx.update(USER)
                .set(USER.FIRST_NAME, user.getFirstName())
                .set(USER.LAST_NAME, user.getLastName())
                .set(USER.BIRTH_DATE, user.getBirthDate())
                .set(USER.EMAIL, user.getEmail())
                .set(USER.PHONE_NUMBER, user.getPhoneNumber())
                .where(USER.USER_ID.eq(user.getUserId()))
                .execute();
    }
}
