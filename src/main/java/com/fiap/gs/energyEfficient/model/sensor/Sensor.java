package com.fiap.gs.energyEfficient.model.sensor;

import com.fiap.gs.energyEfficient.model.morador.Morador;
import com.fiap.gs.energyEfficient.model.sensor.dto.AtualizarSensorDTO;
import com.fiap.gs.energyEfficient.model.sensor.dto.CriarSensorDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "sensor")
    private List<Medicao> medicoes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cd_morador")
    private Morador morador;

    public Sensor(CriarSensorDTO dto){
        this.nome = dto.nome();
        medicoes = new ArrayList<>();
    }

    public void atualizar(AtualizarSensorDTO dto){
        this.nome = dto.nome();
    }
}
