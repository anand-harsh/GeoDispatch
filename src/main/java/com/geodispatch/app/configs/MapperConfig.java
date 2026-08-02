package com.geodispatch.app.configs;

import com.geodispatch.app.dto.PointDto;
import com.geodispatch.app.utils.GeometryUtil;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper mapper = new ModelMapper();

        mapper.typeMap(PointDto.class, Point.class)
                .setConverter(context ->
                        GeometryUtil.createPoint(context.getSource()));

        mapper.typeMap(Point.class, PointDto.class)
                .setConverter(context -> {

                    Point point = context.getSource();

                    return new PointDto(new double[]{
                            point.getX(),
                            point.getY()
                    });
                });

        return mapper;
    }
}