package com.auto_parts_online_shop.api.Part;

import com.auto_parts_online_shop.controller.PartController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PartApiGetAllTest {

    @Autowired
    PartController partController;

    @Test
    void getAll() {
        partController.getAll();
    }
}
