package com.example.toolrental.controllers;

import com.example.toolrental.entities.Tool;
import com.example.toolrental.services.ToolService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/tools")
public class ToolController {
    private final ToolService toolService;

    public ToolController(ToolService toolService) {
        this.toolService = toolService;
    }

    @GetMapping
    public List<Tool> getAllTools() {
        return toolService.findAll();
    }

    @GetMapping("{code}")
    public Tool getToolByCode(@PathVariable String toolCode) {
        return toolService.findToolByCode(toolCode);
    }

}
