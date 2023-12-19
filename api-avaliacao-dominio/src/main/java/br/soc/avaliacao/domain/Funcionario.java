package br.soc.avaliacao.domain;

import static java.util.Optional.ofNullable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FUNCIONARIO")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private LocalDateTime dataCadastro;

    private LocalDateTime dataAtualizacao;

    private UUID idExterno;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.REMOVE)
    private List<ExameRealizado> examesRealizados;

    @PrePersist
    public void prePersist() {
        this.dataCadastro = ofNullable(dataCadastro).orElse(LocalDateTime.now());
        this.idExterno = UUID.randomUUID();
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAtualizacao = ofNullable(dataAtualizacao).orElse(LocalDateTime.now());
    }

}
