package com.auto_parts_online_shop.api.make.api.Make;

import com.auto_parts_online_shop.controller.MakeController;
import com.auto_parts_online_shop.exception.FormatException.FormatException;
import com.auto_parts_online_shop.exception.NotFoundException.NotFoundException;
import com.auto_parts_online_shop.model.Make;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MakeApiDeleteTest {

    @Autowired
    private MakeController makeController;

    @Test
    void deleteMake() {
        Make make = Make.builder().name("Samsung").build();
        Make savedMake = makeController.createManufacturer(make).getBody();

        Make findMake = makeController.getManufacturer(savedMake.getId().toString()).getBody();
        makeController.delete(findMake.getId().toString());
    }

    @Test
    void deleteMakeInvalidInformationThrowNotFoundException() {
        long index = 0;
        while (true) {
            try {
                makeController.getManufacturer(String.valueOf(index));
            } catch (NotFoundException e) {
                try {
                    makeController.delete(String.valueOf(index));
                    break;
                } catch (NotFoundException ignore) {
                    return;
                }
            }
            index++;
        }

        assertEquals("1", "2");
    }

    @Test
    void deleteMakeStringValueThrowFormatException() {
        try {
            makeController.delete("a");
            assertEquals("1", "2");
        } catch (FormatException e) { return; }
    }
}
