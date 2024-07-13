package com.example.toolrental.config;

import com.example.toolrental.dao.ToolChargeRepository;
import com.example.toolrental.dao.ToolRepository;
import com.example.toolrental.entities.Tool;
import com.example.toolrental.entities.ToolCharge;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
/*
*   Added method run to initialize and load in memory h2 db with the required table and init values
*
* */
@Component
public class AppInit implements CommandLineRunner {

    private final ToolRepository toolRepository;
    private final ToolChargeRepository toolChargeRepository;

    public AppInit(ToolRepository toolRepository, ToolChargeRepository toolChargeRepository) {
        this.toolRepository = toolRepository;
        this.toolChargeRepository = toolChargeRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        if (toolRepository.count() == 0) {
            toolRepository.saveAll(
                    List.of(
                            new Tool("CHNS", "Chainsaw", "Stihl"),
                            new Tool("LADW", "Ladder", "Werner"),
                            new Tool("JAKD", "Jackhammer", "Dewalt"),
                            new Tool("JAKR", "Jackhammer", "Ridgid")
                    )
            ).forEach(System.out::println);
        }
        if (toolChargeRepository.count() == 0) {
            toolChargeRepository.saveAll(
                    List.of(
                            new ToolCharge("Ladder", 1.99, true, true, false),
                            new ToolCharge("Chainsaw", 1.49, true, false, true),
                            new ToolCharge("Jackhammer", 2.99, true, false, true)

                    )
            ).forEach(System.out::println);
        }
    }
}
