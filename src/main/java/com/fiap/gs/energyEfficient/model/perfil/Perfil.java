package com.fiap.gs.energyEfficient.model.perfil;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "TB_GS_PERFIL")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gs_perfil_seq")
    @SequenceGenerator(name = "gs_perfil_seq", sequenceName = "tb_gs_perfil_seq", allocationSize = 1)
    @Column(name = "cd_perfil")
    private Long id;

    @Column(name = "nm_perfil", nullable = false)
    private String nome;
}
