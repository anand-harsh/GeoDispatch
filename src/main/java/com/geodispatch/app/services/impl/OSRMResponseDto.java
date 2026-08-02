package com.geodispatch.app.services.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
class OSRMResponseDto {

    private List<OSRMRoute> routes;
}