package com.example.toolrental.services;

import com.example.toolrental.entities.Tool;

import java.util.List;

public interface ToolService {
    List<Tool> findAll();

    Tool findToolByCode(String toolCode);
}
