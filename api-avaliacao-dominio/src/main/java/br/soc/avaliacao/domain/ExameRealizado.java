package br.soc.avaliacao.domain;

import static java.util.Optional.ofNullable;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@Table(name = "EXAMES_REALIZADOS", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"dataRealizacao", "funcionario_id", "exame_id"})
})
public class ExameRealizado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataRealizacao;

    private LocalDateTime dataCadastro;

    private LocalDateTime dataAtualizacao;

    private UUID idExterno;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", referencedColumnName = "id")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "exame_id", referencedColumnName = "id")
    private Exame exame;

    @PrePersist
    public void prePersist() {
        this.dataCadastro = ofNullable(dataCadastro).orElse(LocalDateTime.now());
        this.idExterno = UUID.randomUUID();
    }

    @PreUpdate
    public void preUpdate() {
        dataAtualizacao = ofNullable(dataAtualizacao).orElse(LocalDateTime.now());
    }

    public String getNomeExame() {
        return exame.getNome();
    }

    public String getNomeFuncionario() {
        return funcionario.getNome();
    }

    public UUID getIdFuncionario() {
        return funcionario.getIdExterno();
    }

    public Long getCodigoExame() {
        return exame.getId();
    }

}
