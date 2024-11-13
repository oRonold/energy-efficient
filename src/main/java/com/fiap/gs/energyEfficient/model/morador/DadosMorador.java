package com.fiap.gs.energyEfficient.model.morador;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "TB_GS_DADOS_MORADOR")
public class DadosMorador {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gs_dados_morador_seq")
    @SequenceGenerator(name = "gs_dados_morador_seq", sequenceName = "tb_gs_dados_morador_seq", allocationSize = 1)
    @Column(name = "cd_dados_morador")
    private Long id;

    @Column(name = "nr_cpf", nullable = false, unique = true, length = 15)
    private String cpf;

    @Column(name = "ds_senha", nullable = false)
    private String senha;

    @Column(name = "dt_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "nr_rg", nullable = false, unique = true, length = 15)
    private String rg;

    @OneToOne
    @JoinColumn(name = "cd_morador")
    private Morador morador;

}
