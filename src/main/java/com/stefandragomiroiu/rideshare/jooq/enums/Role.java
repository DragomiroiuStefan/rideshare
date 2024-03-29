/*
 * This file is generated by jOOQ.
 */
package com.stefandragomiroiu.rideshare.jooq.enums;


import com.stefandragomiroiu.rideshare.jooq.Public;

import org.jooq.Catalog;
import org.jooq.EnumType;
import org.jooq.Schema;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public enum Role implements EnumType {

    USER("USER"),

    ADMIN("ADMIN");

    private final String literal;

    private Role(String literal) {
        this.literal = literal;
    }

    @Override
    public Catalog getCatalog() {
        return getSchema().getCatalog();
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public String getName() {
        return "role";
    }

    @Override
    public String getLiteral() {
        return literal;
    }

    /**
     * Lookup a value of this EnumType by its literal
     */
    public static Role lookupLiteral(String literal) {
        return EnumType.lookupLiteral(Role.class, literal);
    }
}
