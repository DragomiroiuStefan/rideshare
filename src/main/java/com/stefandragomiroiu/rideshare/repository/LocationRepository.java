package com.stefandragomiroiu.rideshare.repository;

import com.stefandragomiroiu.rideshare.jooq.tables.daos.LocationDao;
import com.stefandragomiroiu.rideshare.jooq.tables.pojos.Location;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.stefandragomiroiu.rideshare.jooq.Tables.LOCATION;

@Transactional(readOnly = true)
@Repository
public class LocationRepository extends LocationDao {

    private final DSLContext ctx;

    public LocationRepository(DSLContext ctx) {
        super(ctx.configuration());
        this.ctx = ctx;
    }

    public List<Location> findByCityContaining(String city) {
        return ctx.selectFrom(LOCATION)
                .where(LOCATION.CITY.containsIgnoreCase(city))
                .fetchInto(Location.class);
    }
}
