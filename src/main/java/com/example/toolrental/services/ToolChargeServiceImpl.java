package com.example.toolrental.services;

import com.example.toolrental.dao.ToolChargeRepository;
import com.example.toolrental.entities.ToolCharge;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Transactional
public class ToolChargeServiceImpl implements ToolChargeService{

    private final ToolChargeRepository toolChargeRepository;

    public ToolChargeServiceImpl(ToolChargeRepository toolChargeRepository) {
        this.toolChargeRepository = toolChargeRepository;
    }

    @Override
    public ToolCharge findToolChargeByToolType(String toolType) throws NoSuchElementException {
        return toolChargeRepository.findByToolType(toolType).orElseThrow( () -> new NoSuchElementException("Tool charge for tool type "+toolType+" not found"));
    }
}
