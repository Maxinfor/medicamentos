package com.lembremed.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "medicamentos")
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String nome;
    
    @Column(nullable = false, length = 50)
    private String dosagem;
    
    @Column(nullable = false)
    private LocalTime horario;
    
    @Column(length = 20)
    private String frequencia;
    
    @Column(length = 500)
    private String observacoes;
    
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String imagemBase64;
    
    private boolean tomado;
    private LocalDateTime ultimoTomado;
    private LocalDateTime dataCriacao;

    // Getters e Setters omitidos por brevidade (mantenha os que você já tem)
}
