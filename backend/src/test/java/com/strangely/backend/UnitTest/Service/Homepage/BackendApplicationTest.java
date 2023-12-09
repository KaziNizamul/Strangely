package com.strangely.backend.UnitTest.Service.Homepage;

import com.strangely.backend.BackendApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendApplicationTests {

    @Test
    void contextLoads() {
        // This test ensures that the Spring context loads without errors
        BackendApplication.main(new String[]{});
    }
}
