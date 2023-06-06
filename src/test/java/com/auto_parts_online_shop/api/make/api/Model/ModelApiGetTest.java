package com.auto_parts_online_shop.api.make.api.Model;

import com.auto_parts_online_shop.controller.MakeController;
import com.auto_parts_online_shop.controller.ModelController;
import com.auto_parts_online_shop.dto.ModelDto;
import com.auto_parts_online_shop.exception.FormatException.FormatException;
import com.auto_parts_online_shop.exception.NotFoundException.NotFoundException;
import com.auto_parts_online_shop.model.Make;
import com.auto_parts_online_shop.model.Model;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ModelApiGetTest {

    @Autowired
    private ModelController modelController;

    @Autowired
    private MakeController makeController;

    @Test
    void get() {
        Make make = Make.builder().name("Samsung").build();
        Make savedMake = makeController.createManufacturer(make).getBody();

        ModelDto modelDto = ModelDto.builder()
                .name("Galaxy")
                .makeId(savedMake.getId())
                .build();
        Model savedModel = modelController.create(modelDto).getBody();


        Model findModel = modelController.get(savedModel.getId().toString()).getBody();

        assertEquals(findModel.getId(), savedModel.getId());
        assertEquals(findModel.getName(), savedModel.getName());
        assertEquals(findModel.getMake().getId(), savedModel.getMake().getId());

        modelController.delete(savedModel.getId().toString());
        makeController.delete(savedMake.getId().toString());
    }

    @Test
    void getStringInputThrowFormatException() {
        try {
            modelController.get("a");
        } catch (FormatException e) { return; }

        assertEquals("1", "2");
    }

    @Test
    void getInvalidInformationThrowNotFoundException() {
        long index = 0;
        try {
            while (true) {
                modelController.get(String.valueOf(index));
                index++;
            }
        } catch (NotFoundException ignored) { }
    }
}
