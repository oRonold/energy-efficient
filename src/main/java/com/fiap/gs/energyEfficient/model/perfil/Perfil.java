package com.fiap.gs.energyEfficient.model.perfil;

import com.fiap.gs.energyEfficient.model.morador.Morador;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "TB_GS_PERFIL")
public class Perfil {

    @Id
    @Column(name = "cd_perfil")
    private Long id;

    @Column(name = "nm_perfil", nullable = false)
    private String nome;

    @ManyToMany(mappedBy = "perfis", fetch = FetchType.EAGER)
    private Set<Morador> moradores = new HashSet<>();

}
