package com.stefandragomiroiu.rideshare.repository;

import com.stefandragomiroiu.rideshare.jooq.tables.daos.BookingDao;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class BookingRepository extends BookingDao {

    public BookingRepository(DSLContext ctx) {
        super(ctx.configuration());
    }

}
