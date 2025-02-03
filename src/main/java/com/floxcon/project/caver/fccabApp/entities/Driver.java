package com.floxcon.project.caver.fccabApp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

@Entity
@Getter
@Setter
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    // connect user to rider, rider with id 1 can connect with only one user with id 3 and cant connect to other users
    @JoinColumn(name = "user_id") // here user is User table and id is id of user so combined as user_id
    private User user;


    private Double rating;
    private Boolean available;
    @Column(columnDefinition = "Geometry(Point, 4326)") // 4326 is for earth
    Point currentLocation;

}
