package com.stefandragomiroiu.rideshare.controller.validators;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.core.Constraint;
import am.ik.yavi.core.Validator;
import com.stefandragomiroiu.rideshare.tables.pojos.Vehicle;
import org.springframework.stereotype.Component;

import java.time.Year;

@Component
public class VehicleValidator implements RequestValidator<Vehicle> {

    private final Validator<Vehicle> validator;

    public VehicleValidator() {
        this.validator = ValidatorBuilder.<Vehicle>of()
                .constraint(Vehicle::getPlateNumber, "plateNumber", plateNumber -> plateNumber.notBlank().lessThanOrEqual(20))
                .constraint(Vehicle::getBrand, "brand", brand -> brand.notBlank().lessThanOrEqual(50))
                .constraint(Vehicle::getModel, "model", model -> model.notBlank().lessThanOrEqual(50))
                .constraint(Vehicle::getColor, "color", color -> color.notBlank().lessThanOrEqual(50))
                .constraint(Vehicle::getRegistrationYear, "registrationYear", registrationYear -> registrationYear.notNull().lessThanOrEqual(Year.now().getValue()))
                .constraint(Vehicle::getSeats, "seats", seats -> seats.notNull().greaterThan(0))
                .constraint(Vehicle::getOwner, "owner", Constraint::notNull)
                .build();
    }

    @Override
    public Validator<Vehicle> getValidatorInstance() {
        return validator;
    }

}
