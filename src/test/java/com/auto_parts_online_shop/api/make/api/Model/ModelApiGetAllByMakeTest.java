package com.auto_parts_online_shop.api.make.api.Model;

import com.auto_parts_online_shop.controller.MakeController;
import com.auto_parts_online_shop.controller.ModelController;
import com.auto_parts_online_shop.exception.FormatException.FormatException;
import com.auto_parts_online_shop.model.Make;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ModelApiGetAllByMakeTest {

    @Autowired
    private MakeController makeController;

    @Autowired
    private ModelController modelController;

    @Test
    void getAllByMake() {
        Make make = Make.builder().name("Samsung").build();
        Make savedMake = makeController.createManufacturer(make).getBody();

        modelController.getAllByMake(savedMake.getId().toString());

        makeController.delete(make.getId().toString());
    }

    @Test
    void getAllByMakeStringInputThrowFormatException() {
        try {
            modelController.getAllByMake("a");
            assertEquals("1", "2");
        } catch (FormatException ignore) {
        }
    }
}
