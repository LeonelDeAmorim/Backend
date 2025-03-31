package com.project;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import application.ProjectApplication;

@SpringBootTest(classes = ProjectApplication.class)  // Specify your main application class
class ProjectApplicationTests {

    @Test
    void contextLoads() {
        // This test will simply check if the application context loads correctly
    }
}
