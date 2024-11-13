package com.fiap.gs.energyEfficient.model.sensor;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "TB_GS_SENSOR")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gs_sensor_seq")
    @SequenceGenerator(name = "gs_sensor_seq", sequenceName = "tb_gs_sensor_seq", allocationSize = 1)
    @Column(name = "cd_sensor")
    private Long id;

    @Column(name = "nm_sensor", nullable = false)
    private String nome;
}
