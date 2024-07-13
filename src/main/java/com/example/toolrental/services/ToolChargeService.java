package com.example.toolrental.services;

import com.example.toolrental.entities.ToolCharge;

public interface ToolChargeService {
    ToolCharge findToolChargeByToolType(String toolType);
}
