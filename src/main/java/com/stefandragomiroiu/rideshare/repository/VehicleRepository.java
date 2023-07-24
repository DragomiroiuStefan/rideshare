package com.stefandragomiroiu.rideshare.repository;

import com.stefandragomiroiu.rideshare.tables.daos.VehicleDao;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class VehicleRepository extends VehicleDao {

    public VehicleRepository(DSLContext ctx) {
        super(ctx.configuration());
    }

}
