/*
 * This file is generated by jOOQ.
 */
package com.stefandragomiroiu.rideshare.jooq.tables;


import com.stefandragomiroiu.rideshare.jooq.Keys;
import com.stefandragomiroiu.rideshare.jooq.Public;
import com.stefandragomiroiu.rideshare.jooq.tables.records.BookingConnectionRecord;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function2;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row2;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class BookingConnection extends TableImpl<BookingConnectionRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.booking_connection</code>
     */
    public static final BookingConnection BOOKING_CONNECTION = new BookingConnection();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BookingConnectionRecord> getRecordType() {
        return BookingConnectionRecord.class;
    }

    /**
     * The column <code>public.booking_connection.booking_id</code>.
     */
    public final TableField<BookingConnectionRecord, Long> BOOKING_ID = createField(DSL.name("booking_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.booking_connection.connection_id</code>.
     */
    public final TableField<BookingConnectionRecord, Long> CONNECTION_ID = createField(DSL.name("connection_id"), SQLDataType.BIGINT.nullable(false), this, "");

    private BookingConnection(Name alias, Table<BookingConnectionRecord> aliased) {
        this(alias, aliased, null);
    }

    private BookingConnection(Name alias, Table<BookingConnectionRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.booking_connection</code> table reference
     */
    public BookingConnection(String alias) {
        this(DSL.name(alias), BOOKING_CONNECTION);
    }

    /**
     * Create an aliased <code>public.booking_connection</code> table reference
     */
    public BookingConnection(Name alias) {
        this(alias, BOOKING_CONNECTION);
    }

    /**
     * Create a <code>public.booking_connection</code> table reference
     */
    public BookingConnection() {
        this(DSL.name("booking_connection"), null);
    }

    public <O extends Record> BookingConnection(Table<O> child, ForeignKey<O, BookingConnectionRecord> key) {
        super(child, key, BOOKING_CONNECTION);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<BookingConnectionRecord> getPrimaryKey() {
        return Keys.BOOKING_CONNECTION_PKEY;
    }

    @Override
    public List<ForeignKey<BookingConnectionRecord, ?>> getReferences() {
        return Arrays.asList(Keys.BOOKING_CONNECTION__BOOKING_CONNECTION_BOOKING_ID_FKEY, Keys.BOOKING_CONNECTION__BOOKING_CONNECTION_CONNECTION_ID_FKEY);
    }

    private transient Booking _booking;
    private transient RideConnection _rideConnection;

    /**
     * Get the implicit join path to the <code>public.booking</code> table.
     */
    public Booking booking() {
        if (_booking == null)
            _booking = new Booking(this, Keys.BOOKING_CONNECTION__BOOKING_CONNECTION_BOOKING_ID_FKEY);

        return _booking;
    }

    /**
     * Get the implicit join path to the <code>public.ride_connection</code>
     * table.
     */
    public RideConnection rideConnection() {
        if (_rideConnection == null)
            _rideConnection = new RideConnection(this, Keys.BOOKING_CONNECTION__BOOKING_CONNECTION_CONNECTION_ID_FKEY);

        return _rideConnection;
    }

    @Override
    public BookingConnection as(String alias) {
        return new BookingConnection(DSL.name(alias), this);
    }

    @Override
    public BookingConnection as(Name alias) {
        return new BookingConnection(alias, this);
    }

    @Override
    public BookingConnection as(Table<?> alias) {
        return new BookingConnection(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public BookingConnection rename(String name) {
        return new BookingConnection(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public BookingConnection rename(Name name) {
        return new BookingConnection(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public BookingConnection rename(Table<?> name) {
        return new BookingConnection(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<Long, Long> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function2<? super Long, ? super Long, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function2<? super Long, ? super Long, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}