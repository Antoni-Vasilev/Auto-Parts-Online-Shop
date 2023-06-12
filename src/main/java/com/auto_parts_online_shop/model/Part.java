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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "models_id", referencedColumnName = "id", nullable = false)
    private List<Model> modelList;

    @Override
    public boolean equals(Object obj) {
        Part part = (Part) obj;

        if (!id.equals(part.id)) return false;
        if (!name.equals(part.name)) return false;
        if (!description.equals(part.description)) return false;
        if (!price.equals(part.price)) return false;
        if (!partCategory.getId().equals(part.partCategory.getId())) return false;
        for (int i = 0; i < modelList.size(); i++) {
            if (!modelList.get(i).getId().equals(part.getModelList().get(i).getId())) return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
