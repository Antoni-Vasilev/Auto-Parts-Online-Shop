package com.auto_parts_online_shop.api.make.api.Make;

import com.auto_parts_online_shop.controller.MakeController;
import com.auto_parts_online_shop.model.Make;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MakeApiCreateTest {

    @Autowired
    private MakeController makeController;

    @Test
    void createMake() {
        Make make = Make.builder().name("Samsung").build();
        Make savedMake = makeController.createManufacturer(make).getBody();
        Make findMake = makeController.getManufacturer(savedMake.getId().toString()).getBody();
        if (findMake == null) assertEquals("1", "2");
        makeController.delete(findMake.getId().toString());
    }
}
