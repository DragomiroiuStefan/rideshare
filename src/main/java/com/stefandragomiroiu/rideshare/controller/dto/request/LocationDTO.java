package com.stefandragomiroiu.rideshare.controller.dto.request;

import com.stefandragomiroiu.rideshare.controller.dto.validation.Update;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LocationDTO(
        @NotNull(groups = {Update.class})
        Long locationId,
        @NotBlank
        @Size(max = 255)
        String city,
        @NotBlank
        @Size(max = 255)
        String county
) {
}
