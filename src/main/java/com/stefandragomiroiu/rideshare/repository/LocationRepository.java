package com.stefandragomiroiu.rideshare.repository;

import com.stefandragomiroiu.rideshare.tables.daos.LocationDao;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class LocationRepository extends LocationDao {

    public LocationRepository(DSLContext ctx) {
        super(ctx.configuration());
    }

}
