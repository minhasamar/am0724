package com.example.toolrental.services;

import com.example.toolrental.dao.ToolRepository;
import com.example.toolrental.entities.Tool;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class ToolServiceImpl implements ToolService{
    private final ToolRepository toolRepository;

    public ToolServiceImpl(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    @Override
    public List<Tool> findAll() {
        return toolRepository.findAll();
    }

    @Override
    public Tool findToolByCode(String toolCode) throws NoSuchElementException {
        return toolRepository.findByToolCode(toolCode).orElseThrow(
                () -> new NoSuchElementException("Tool with code "+toolCode+" does not exist")
        ) ;
    }

}
