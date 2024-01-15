/*
 * This file is generated by jOOQ.
 */
package com.stefandragomiroiu.rideshare.jooq.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class BookingConnection implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long bookingId;
    private Long connectionId;

    public BookingConnection() {}

    public BookingConnection(BookingConnection value) {
        this.bookingId = value.bookingId;
        this.connectionId = value.connectionId;
    }

    public BookingConnection(
        Long bookingId,
        Long connectionId
    ) {
        this.bookingId = bookingId;
        this.connectionId = connectionId;
    }

    /**
     * Getter for <code>public.booking_connection.booking_id</code>.
     */
    public Long getBookingId() {
        return this.bookingId;
    }

    /**
     * Setter for <code>public.booking_connection.booking_id</code>.
     */
    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    /**
     * Getter for <code>public.booking_connection.connection_id</code>.
     */
    public Long getConnectionId() {
        return this.connectionId;
    }

    /**
     * Setter for <code>public.booking_connection.connection_id</code>.
     */
    public void setConnectionId(Long connectionId) {
        this.connectionId = connectionId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final BookingConnection other = (BookingConnection) obj;
        if (this.bookingId == null) {
            if (other.bookingId != null)
                return false;
        }
        else if (!this.bookingId.equals(other.bookingId))
            return false;
        if (this.connectionId == null) {
            if (other.connectionId != null)
                return false;
        }
        else if (!this.connectionId.equals(other.connectionId))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.bookingId == null) ? 0 : this.bookingId.hashCode());
        result = prime * result + ((this.connectionId == null) ? 0 : this.connectionId.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("BookingConnection (");

        sb.append(bookingId);
        sb.append(", ").append(connectionId);

        sb.append(")");
        return sb.toString();
    }
}
