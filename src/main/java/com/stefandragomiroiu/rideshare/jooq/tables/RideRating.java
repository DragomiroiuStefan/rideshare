/*
 * This file is generated by jOOQ.
 */
package com.stefandragomiroiu.rideshare.jooq.tables;


import com.stefandragomiroiu.rideshare.jooq.Keys;
import com.stefandragomiroiu.rideshare.jooq.Public;
import com.stefandragomiroiu.rideshare.jooq.tables.records.RideRatingRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function5;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row5;
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
public class RideRating extends TableImpl<RideRatingRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.ride_rating</code>
     */
    public static final RideRating RIDE_RATING = new RideRating();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RideRatingRecord> getRecordType() {
        return RideRatingRecord.class;
    }

    /**
     * The column <code>public.ride_rating.ride_id</code>.
     */
    public final TableField<RideRatingRecord, Long> RIDE_ID = createField(DSL.name("ride_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.ride_rating.user_id</code>.
     */
    public final TableField<RideRatingRecord, Long> USER_ID = createField(DSL.name("user_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.ride_rating.rating</code>.
     */
    public final TableField<RideRatingRecord, Integer> RATING = createField(DSL.name("rating"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.ride_rating.comment</code>.
     */
    public final TableField<RideRatingRecord, String> COMMENT = createField(DSL.name("comment"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>public.ride_rating.posted_at</code>.
     */
    public final TableField<RideRatingRecord, LocalDateTime> POSTED_AT = createField(DSL.name("posted_at"), SQLDataType.LOCALDATETIME(6).defaultValue(DSL.field(DSL.raw("now()"), SQLDataType.LOCALDATETIME)), this, "");

    private RideRating(Name alias, Table<RideRatingRecord> aliased) {
        this(alias, aliased, null);
    }

    private RideRating(Name alias, Table<RideRatingRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.ride_rating</code> table reference
     */
    public RideRating(String alias) {
        this(DSL.name(alias), RIDE_RATING);
    }

    /**
     * Create an aliased <code>public.ride_rating</code> table reference
     */
    public RideRating(Name alias) {
        this(alias, RIDE_RATING);
    }

    /**
     * Create a <code>public.ride_rating</code> table reference
     */
    public RideRating() {
        this(DSL.name("ride_rating"), null);
    }

    public <O extends Record> RideRating(Table<O> child, ForeignKey<O, RideRatingRecord> key) {
        super(child, key, RIDE_RATING);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<RideRatingRecord> getPrimaryKey() {
        return Keys.RIDE_RATING_PKEY;
    }

    @Override
    public List<ForeignKey<RideRatingRecord, ?>> getReferences() {
        return Arrays.asList(Keys.RIDE_RATING__RIDE_RATING_RIDE_ID_FKEY, Keys.RIDE_RATING__RIDE_RATING_USER_ID_FKEY);
    }

    private transient Ride _ride;
    private transient User _user;

    /**
     * Get the implicit join path to the <code>public.ride</code> table.
     */
    public Ride ride() {
        if (_ride == null)
            _ride = new Ride(this, Keys.RIDE_RATING__RIDE_RATING_RIDE_ID_FKEY);

        return _ride;
    }

    /**
     * Get the implicit join path to the <code>public.user</code> table.
     */
    public User user() {
        if (_user == null)
            _user = new User(this, Keys.RIDE_RATING__RIDE_RATING_USER_ID_FKEY);

        return _user;
    }

    @Override
    public RideRating as(String alias) {
        return new RideRating(DSL.name(alias), this);
    }

    @Override
    public RideRating as(Name alias) {
        return new RideRating(alias, this);
    }

    @Override
    public RideRating as(Table<?> alias) {
        return new RideRating(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public RideRating rename(String name) {
        return new RideRating(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public RideRating rename(Name name) {
        return new RideRating(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public RideRating rename(Table<?> name) {
        return new RideRating(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<Long, Long, Integer, String, LocalDateTime> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function5<? super Long, ? super Long, ? super Integer, ? super String, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function5<? super Long, ? super Long, ? super Integer, ? super String, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
