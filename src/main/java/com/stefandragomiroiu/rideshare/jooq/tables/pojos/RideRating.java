/*
 * This file is generated by jOOQ.
 */
package com.stefandragomiroiu.rideshare.jooq.tables.pojos;


import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class RideRating implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long rideId;
    private Long userId;
    private Integer rating;
    private String comment;
    private LocalDateTime postedAt;

    public RideRating() {}

    public RideRating(RideRating value) {
        this.rideId = value.rideId;
        this.userId = value.userId;
        this.rating = value.rating;
        this.comment = value.comment;
        this.postedAt = value.postedAt;
    }

    public RideRating(
        Long rideId,
        Long userId,
        Integer rating,
        String comment,
        LocalDateTime postedAt
    ) {
        this.rideId = rideId;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
        this.postedAt = postedAt;
    }

    /**
     * Getter for <code>public.ride_rating.ride_id</code>.
     */
    public Long getRideId() {
        return this.rideId;
    }

    /**
     * Setter for <code>public.ride_rating.ride_id</code>.
     */
    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }

    /**
     * Getter for <code>public.ride_rating.user_id</code>.
     */
    public Long getUserId() {
        return this.userId;
    }

    /**
     * Setter for <code>public.ride_rating.user_id</code>.
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Getter for <code>public.ride_rating.rating</code>.
     */
    public Integer getRating() {
        return this.rating;
    }

    /**
     * Setter for <code>public.ride_rating.rating</code>.
     */
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    /**
     * Getter for <code>public.ride_rating.comment</code>.
     */
    public String getComment() {
        return this.comment;
    }

    /**
     * Setter for <code>public.ride_rating.comment</code>.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Getter for <code>public.ride_rating.posted_at</code>.
     */
    public LocalDateTime getPostedAt() {
        return this.postedAt;
    }

    /**
     * Setter for <code>public.ride_rating.posted_at</code>.
     */
    public void setPostedAt(LocalDateTime postedAt) {
        this.postedAt = postedAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final RideRating other = (RideRating) obj;
        if (this.rideId == null) {
            if (other.rideId != null)
                return false;
        }
        else if (!this.rideId.equals(other.rideId))
            return false;
        if (this.userId == null) {
            if (other.userId != null)
                return false;
        }
        else if (!this.userId.equals(other.userId))
            return false;
        if (this.rating == null) {
            if (other.rating != null)
                return false;
        }
        else if (!this.rating.equals(other.rating))
            return false;
        if (this.comment == null) {
            if (other.comment != null)
                return false;
        }
        else if (!this.comment.equals(other.comment))
            return false;
        if (this.postedAt == null) {
            if (other.postedAt != null)
                return false;
        }
        else if (!this.postedAt.equals(other.postedAt))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.rideId == null) ? 0 : this.rideId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.rating == null) ? 0 : this.rating.hashCode());
        result = prime * result + ((this.comment == null) ? 0 : this.comment.hashCode());
        result = prime * result + ((this.postedAt == null) ? 0 : this.postedAt.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("RideRating (");

        sb.append(rideId);
        sb.append(", ").append(userId);
        sb.append(", ").append(rating);
        sb.append(", ").append(comment);
        sb.append(", ").append(postedAt);

        sb.append(")");
        return sb.toString();
    }
}
