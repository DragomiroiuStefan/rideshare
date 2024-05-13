package com.stefandragomiroiu.rideshare.controller.mapper;

import com.stefandragomiroiu.rideshare.controller.dto.request.LocationDTO;
import com.stefandragomiroiu.rideshare.jooq.tables.pojos.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationDTO toDTO(Location location);
    Location toEntity(LocationDTO locationDTO);
}
