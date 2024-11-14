package com.fiap.gs.energyEfficient.model.morador;

import com.fiap.gs.energyEfficient.model.morador.dto.CriarMoradorDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "TB_GS_CONTATO_MORADOR")
public class ContatoMorador {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gs_contato_morador_seq")
    @SequenceGenerator(name = "gs_contato_morador_seq", sequenceName = "tb_gs_contato_morador_seq", allocationSize = 1)
    @Column(name = "cd_contato_morador")
    private Long id;

    @Column(name = "ds_email", nullable = false, unique = true)
    private String email;

    @Column(name = "nr_telefone", nullable = false, unique = true, length = 15)
    private String telefone;

    @OneToOne
    @JoinColumn(name = "cd_morador")
    private Morador morador;

    public ContatoMorador(CriarMoradorDTO dto){
        this.email = dto.email();
        this.telefone = dto.telefone();
    }

}
