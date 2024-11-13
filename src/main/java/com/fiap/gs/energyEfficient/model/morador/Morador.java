package com.fiap.gs.energyEfficient.model.morador;

import com.fiap.gs.energyEfficient.model.perfil.Perfil;
import com.fiap.gs.energyEfficient.model.sensor.Sensor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

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

    @OneToOne(mappedBy = "morador", cascade = CascadeType.ALL)
    private DadosMorador dadosMorador;

    @OneToOne(mappedBy = "morador", cascade = CascadeType.ALL)
    private ContatoMorador contatoMorador;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "TB_GS_MORADOR_PERFIL",
            joinColumns = @JoinColumn(name = "cd_morador"),
            inverseJoinColumns = @JoinColumn(name = "cd_perfil")
    )
    private Set<Perfil> perfis;

    @OneToMany(mappedBy = "morador")
    private List<Sensor> sensores;

}
