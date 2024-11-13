package com.fiap.gs.energyEfficient.model.sensor;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "TB_GS_AVISOS")
public class Avisos {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gs_avisos_seq")
    @SequenceGenerator(name = "gs_avisos_seq", sequenceName = "tb_gs_avisos_seq", allocationSize = 1)
    @Column(name = "cd_avisos")
    private Long id;

    @Column(name = "ds_mensagem", nullable = false)
    private String mensagem;

    @ManyToOne
    @JoinColumn(name = "cd_sensor")
    private Sensor sensor;

}
