package com.stefandragomiroiu.rideshare.repository;

import com.stefandragomiroiu.rideshare.tables.daos.LocationsDao;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class LocationRepository extends LocationsDao {

    public LocationRepository(DSLContext ctx) {
        super(ctx.configuration());
    }

}
