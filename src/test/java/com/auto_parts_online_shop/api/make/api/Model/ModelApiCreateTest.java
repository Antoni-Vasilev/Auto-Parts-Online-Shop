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
public class ModelApiCreateTest {

    @Autowired
    private ModelController modelController;

    @Autowired
    private MakeController makeController;

    @Test
    void create() {
        Make make = Make.builder().name("Samsung").build();
        Make savedMake = makeController.createManufacturer(make).getBody();

        ModelDto modelDto = ModelDto.builder()
                .name("Galaxy")
                .makeId(savedMake.getId())
                .build();

        Model savedModel = modelController.create(modelDto).getBody();

        Model findModel = modelController.get(savedModel.getId().toString()).getBody();

        modelController.delete(findModel.getId().toString());
        makeController.delete(make.getId().toString());
    }
}
