package com.fiap.gs.energyEfficient.model.morador;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "TB_GS_MORADOR")
public class Morador {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gs_morador_seq")
    @SequenceGenerator(name = "gs_morador_seq", sequenceName = "tb_gs_morador_seq", allocationSize = 1)
    @Column(name = "cd_morador")
    private Long id;

    @Column(name = "nm_morador", nullable = false)
    private String nome;

    @Column(name = "ds_idade", nullable = false)
    private Integer idade;

    @Column(name = "ds_estado_civil", nullable = false)
    private String estadoCivil;
}
