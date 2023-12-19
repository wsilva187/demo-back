package br.soc.avaliacao.domain;

import static java.util.Optional.ofNullable;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

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
@Table(name = "EXAME")
public class Exame {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private LocalDateTime dataCadastro;

    private LocalDateTime dataAtualizacao;

    @OneToMany(mappedBy = "exame")
    private List<ExameRealizado> examesRealizados;

    @Transient
    private Long total;

    public Exame(Long id, String nome, Long total) {
        this.id = id;
        this.nome = nome;
        this.total = total;
    }

    @PrePersist
    public void prePersist() {
        dataCadastro = ofNullable(dataCadastro).orElse(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate() {
        dataAtualizacao = ofNullable(dataAtualizacao).orElse(LocalDateTime.now());
    }

}
