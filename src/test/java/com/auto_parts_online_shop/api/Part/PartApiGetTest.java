package com.auto_parts_online_shop.api.Part;

import com.auto_parts_online_shop.controller.MakeController;
import com.auto_parts_online_shop.controller.ModelController;
import com.auto_parts_online_shop.controller.PartCategoryController;
import com.auto_parts_online_shop.controller.PartController;
import com.auto_parts_online_shop.dto.ModelDto;
import com.auto_parts_online_shop.dto.PartDto;
import com.auto_parts_online_shop.model.Make;
import com.auto_parts_online_shop.model.Model;
import com.auto_parts_online_shop.model.Part;
import com.auto_parts_online_shop.model.PartCategory;
import com.mysql.cj.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PartApiGetTest {

    @Autowired
    PartController partController;

    @Autowired
    PartCategoryController partCategoryController;

    @Autowired
    ModelController modelController;

    @Autowired
    MakeController makeController;

    @Test
    void get() {
        Make make = Make.builder()
                .name("Samsung")
                .build();
        Make savedMake = makeController.createManufacturer(make).getBody();

        ModelDto modelDto = ModelDto.builder()
                .name("S23 ULTRA")
                .makeId(savedMake.getId())
                .build();
        Model savedModel = modelController.create(modelDto).getBody();

        PartCategory partCategory = PartCategory.builder()
                .name("Phone")
                .description(null)
                .build();
        PartCategory savedPartCategory = partCategoryController.create(partCategory).getBody();

        PartDto partDto = PartDto.builder()
                .name("X40K11QB")
                .categoryId(savedPartCategory.getId())
                .modelIds(List.of(savedModel.getId()))
                .description(null)
                .price(2300f)
                .build();
        Part savedPart = partController.create(partDto).getBody();

        Part findPart = partController.get(savedPart.getId().toString()).getBody();

        assertEquals(savedPart.getId(), findPart.getId());
        assertEquals(savedPart.getName(), findPart.getName());
        assertEquals(savedPart.getPartCategory().getId(), findPart.getPartCategory().getId());
        assertEquals(savedPart.getDescription(), findPart.getDescription());
        assertEquals(savedPart.getPrice(), findPart.getPrice());
        if (findPart.getModelList().size() != 0) {
            for (int i = 0; i < findPart.getModelList().size(); i++)
                assertEquals(savedPart.getModelList().get(i).getId(), findPart.getModelList().get(i).getId());
        }

//        findPart.equals(savedPart);

        partController.delete(savedPart.getId().toString());
        partCategoryController.delete(savedPartCategory.getId().toString());
        modelController.delete(savedModel.getId().toString());
        makeController.delete(savedMake.getId().toString());
    }
}
