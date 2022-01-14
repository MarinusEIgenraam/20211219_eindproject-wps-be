//package com.willpowered.eindprojectwpsbe.config;
//
//import com.willpowered.eindprojectwpsbe.EindprojectWpsBeApplication;
//import com.willpowered.eindprojectwpsbe.Project.Project;
//import com.willpowered.eindprojectwpsbe.Project.ProjectRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.Random;
//
//@Component
//public class Initializer implements ApplicationRunner {
//
//    private static final Logger log = LoggerFactory.getLogger(EindprojectWpsBeApplication.class);
//
//    @Autowired
//    private ProjectRepository projectRepository;
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        for (int i = 1; i <= 100; i++) {
//            String projectName = String.format("Item_%03d", i);
//            Random r = new Random();
//            char c = (char) (r.nextInt(26) + 'A');
//            String description = String.format("%c - %03d - Another demo item to seed in the database.", c, i);
//            Project project = new Project(projectName, description);
//            projectRepository.save(project);
//        }
//        log.info("Database seeded");
//    }
//
//}
