package kz.springboot.springbootdemoo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "options")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeridianOptions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number_of_gun")
    private int numberOfGun;

    @Column(name = "x")
    private double x;

    @Column(name = "y")
    private double y;

    @Column(name = "number_of_target_one")
    private int numberOfTargetOne;

    @Column(name = "distance_one")
    private double distanceOne;

    @Column(name = "azimuth_one")
    private String azimuthOne;

    @Column(name = "number_of_target_two")
    private int numberOfTargetTwo;

    @Column(name = "distance_two")
    private double distanceTwo;

    @Column(name = "azimuth_two")
    private String azimuthTwo;
}
