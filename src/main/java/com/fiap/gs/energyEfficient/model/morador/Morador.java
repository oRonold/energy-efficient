package com.fiap.gs.energyEfficient.model.morador;

import com.fiap.gs.energyEfficient.model.morador.dto.CriarMoradorDTO;
import com.fiap.gs.energyEfficient.model.perfil.Perfil;
import com.fiap.gs.energyEfficient.model.sensor.Sensor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "TB_GS_MORADOR")
public class Morador implements UserDetails {

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "TB_GS_MORADOR_PERFIL",
            joinColumns = @JoinColumn(name = "cd_morador"),
            inverseJoinColumns = @JoinColumn(name = "cd_perfil")
    )
    private Set<Perfil> perfis = new HashSet<>();

    @OneToMany(mappedBy = "morador", fetch = FetchType.EAGER)
    private List<Sensor> sensores;

    public Morador(CriarMoradorDTO dto){
        this.nome = dto.nome();
        this.idade = dto.idade();
        this.estadoCivil = dto.estadoCivil();
        dadosMorador = new DadosMorador(dto);
        contatoMorador = new ContatoMorador(dto);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return perfis.stream().map(perfil -> new SimpleGrantedAuthority("ROLE_" + perfil.getNome()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return dadosMorador.getSenha();
    }

    @Override
    public String getUsername() {
        return dadosMorador.getCpf();
    }
}
