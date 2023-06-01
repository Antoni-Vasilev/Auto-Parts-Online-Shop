package com.auto_parts_online_shop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "parts")
@Entity
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    private String description;

    @NotNull
    @Column(nullable = false)
    private Float price;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "partCategory_id", referencedColumnName = "id", nullable = false)
    private PartCategory partCategory;

    @NotNull
    @ManyToMany
    @JoinColumn(name = "models_id", referencedColumnName = "id", nullable = false)
    private List<Model> modelList;
}
