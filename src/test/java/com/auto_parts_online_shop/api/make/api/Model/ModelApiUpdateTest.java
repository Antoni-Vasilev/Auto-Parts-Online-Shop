package com.auto_parts_online_shop.api.make.api.Model;

import com.auto_parts_online_shop.controller.MakeController;
import com.auto_parts_online_shop.controller.ModelController;
import com.auto_parts_online_shop.dto.ModelDto;
import com.auto_parts_online_shop.model.Make;
import com.auto_parts_online_shop.model.Model;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ModelApiUpdateTest {

    @Autowired
    private MakeController makeController;

    @Autowired
    private ModelController modelController;

    @Test
    void update() {
        Make make = Make.builder().name("Samsung").build();
        Make savedMake = makeController.createManufacturer(make).getBody();

        ModelDto modelDto = ModelDto.builder()
                .name("Galaxy")
                .makeId(savedMake.getId())
                .build();

        Model savedModel = modelController.create(modelDto).getBody();

        ModelDto updateModel = ModelDto.builder()
                .name("Note")
                .makeId(savedMake.getId())
                .build();

        Model updatedModel = modelController.update(savedModel.getId().toString(), updateModel).getBody();

        modelController.delete(updatedModel.getId().toString());
        makeController.delete(make.getId().toString());
    }
}
