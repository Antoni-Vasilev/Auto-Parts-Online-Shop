package com.auto_parts_online_shop.api.make.api.Make;

import com.auto_parts_online_shop.controller.MakeController;
import com.auto_parts_online_shop.exception.NotFoundException.NotFoundException;
import com.auto_parts_online_shop.model.Make;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MakeApiUpdateTest {

    @Autowired
    private MakeController makeController;

    @Test
    void updateMake() {
        Make make = Make.builder().name("Samsung").build();
        Make savedMake = makeController.createManufacturer(make).getBody();
        Make updateMake = Make.builder().name("Huawei").build();
        Make updatedMake = makeController.updateManufacturer(savedMake.getId().toString(), updateMake).getBody();
        Make findMake = makeController.getManufacturer(savedMake.getId().toString()).getBody();

        makeController.delete(findMake.getId().toString());

        assertEquals(updatedMake.getId(), findMake.getId());
        assertEquals(updatedMake.getName(), findMake.getName());
    }

    @Test
    void updateMakeInvalidInformationThrowNotFoundException() {
        long index = 0;
        while (true) {
            try {
                makeController.getManufacturer(String.valueOf(index));
            } catch (NotFoundException e) {
                try {
                    makeController.updateManufacturer(String.valueOf(index), null);
                    break;
                } catch (NotFoundException ignore) {
                    return;
                }
            }
            index++;
        }

        assertEquals("1", "2");
    }
}
