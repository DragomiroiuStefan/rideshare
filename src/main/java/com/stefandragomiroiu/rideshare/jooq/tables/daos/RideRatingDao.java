/*
 * This file is generated by jOOQ.
 */
package com.stefandragomiroiu.rideshare.jooq.tables.daos;


import com.stefandragomiroiu.rideshare.jooq.AbstractSpringDAOImpl;
import com.stefandragomiroiu.rideshare.jooq.tables.RideRating;
import com.stefandragomiroiu.rideshare.jooq.tables.records.RideRatingRecord;

import java.time.LocalDateTime;
import java.util.List;

import org.jooq.Configuration;
import org.jooq.Record2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
@Repository
public class RideRatingDao extends AbstractSpringDAOImpl<RideRatingRecord, com.stefandragomiroiu.rideshare.jooq.tables.pojos.RideRating, Record2<Long, Long>> {

    /**
     * Create a new RideRatingDao without any configuration
     */
    public RideRatingDao() {
        super(RideRating.RIDE_RATING, com.stefandragomiroiu.rideshare.jooq.tables.pojos.RideRating.class);
    }

    /**
     * Create a new RideRatingDao with an attached configuration
     */
    @Autowired
    public RideRatingDao(Configuration configuration) {
        super(RideRating.RIDE_RATING, com.stefandragomiroiu.rideshare.jooq.tables.pojos.RideRating.class, configuration);
    }

    @Override
    public Record2<Long, Long> getId(com.stefandragomiroiu.rideshare.jooq.tables.pojos.RideRating object) {
        return compositeKeyRecord(object.getRideId(), object.getUserId());
    }

    /**
     * Fetch records that have <code>ride_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.stefandragomiroiu.rideshare.jooq.tables.pojos.RideRating> fetchRangeOfRideId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(RideRating.RIDE_RATING.RIDE_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>ride_id IN (values)</code>
     */
    public List<com.stefandragomiroiu.rideshare.jooq.tables.pojos.RideRating> fetchByRideId(Long... values) {
        return fetch(RideRating.RIDE_RATING.RIDE_ID, values);
    }

    /**
     * Fetch records that have <code>user_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.stefandragomiroiu.rideshare.jooq.tables.pojos.RideRating> fetchRangeOfUserId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(RideRating.RIDE_RATING.USER_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>user_id IN (values)</code>
     */
    public List<com.stefandragomiroiu.rideshare.jooq.tables.pojos.RideRating> fetchByUserId(Long... values) {
        return fetch(RideRating.RIDE_RATING.USER_ID, values);
    }

    /**
     * Fetch records that have <code>rating BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.stefandragomiroiu.rideshare.jooq.tables.pojos.RideRating> fetchRangeOfRating(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(RideRating.RIDE_RATING.RATING, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>rating IN (values)</code>
     */
    public List<com.stefandragomiroiu.rideshare.jooq.tables.pojos.RideRating> fetchByRating(Integer... values) {
        return fetch(RideRating.RIDE_RATING.RATING, values);
    }

    /**
     * Fetch records that have <code>comment BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.stefandragomiroiu.rideshare.jooq.tables.pojos.RideRating> fetchRangeOfComment(String lowerInclusive, String upperInclusive) {
        return fetchRange(RideRating.RIDE_RATING.COMMENT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>comment IN (values)</code>
     */
    public List<com.stefandragomiroiu.rideshare.jooq.tables.pojos.RideRating> fetchByComment(String... values) {
        return fetch(RideRating.RIDE_RATING.COMMENT, values);
    }

    /**
     * Fetch records that have <code>posted_at BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.stefandragomiroiu.rideshare.jooq.tables.pojos.RideRating> fetchRangeOfPostedAt(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(RideRating.RIDE_RATING.POSTED_AT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>posted_at IN (values)</code>
     */
    public List<com.stefandragomiroiu.rideshare.jooq.tables.pojos.RideRating> fetchByPostedAt(LocalDateTime... values) {
        return fetch(RideRating.RIDE_RATING.POSTED_AT, values);
    }
}
