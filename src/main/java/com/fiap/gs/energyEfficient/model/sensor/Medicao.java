package com.fiap.gs.energyEfficient.model.sensor;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "TB_GS_MEDICAO")
public class Medicao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gs_medicao_seq")
    @SequenceGenerator(name = "gs_medicao_seq", sequenceName = "tb_gs_medicao_seq", allocationSize = 1)
    @Column(name = "cd_medicao")
    private Long id;

    @Column(name = "dt_medicao", nullable = false)
    private LocalDateTime dataMedicao;

    @Column(name = "vl_corrente", nullable = false, precision = 5, scale = 2)
    private BigDecimal valorCorrente;

    @Column(name = "vl_tensao", nullable = false, precision = 5, scale = 2)
    private BigDecimal valorTensao;

    @Column(name = "vl_temperatura", nullable = false, precision = 5, scale = 2)
    private BigDecimal valorTemperatura;

    @Column(name = "vl_consumo", nullable = false, precision = 5, scale = 2)
    private BigDecimal valorConsumo;
}
