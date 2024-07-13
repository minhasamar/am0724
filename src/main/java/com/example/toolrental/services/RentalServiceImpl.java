package com.example.toolrental.services;

import com.example.toolrental.dao.RentalRepository;
import com.example.toolrental.entities.Rental;
import com.example.toolrental.entities.RentalRequest;
import com.example.toolrental.entities.Tool;
import com.example.toolrental.entities.ToolCharge;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
@Transactional
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final ToolService toolService;
    private final ToolChargeService toolChargeService;

    public RentalServiceImpl(RentalRepository rentalRepository, ToolService toolService, ToolChargeService toolChargeService) {
        this.rentalRepository = rentalRepository;
        this.toolService = toolService;
        this.toolChargeService = toolChargeService;
    }

    @Override
    public Rental saveRental(RentalRequest request) {
        Rental rental = null;
        try{
            Tool tool = toolService.findToolByCode(request.toolCode());
            rental.setTool(tool);
            ToolCharge toolCharge = toolChargeService.findToolChargeByToolType(tool.getToolType());
            rental.setToolCharge(toolCharge);
            rental.setRentalDays(request.rentalDays());
            rental.setCheckOutDate(request.checkOutDate());
            rental.setDiscountPercent(request.discountPercent());
            //calculations
            Calendar cal = Calendar.getInstance();
            cal.setTime(request.checkOutDate());
            Integer chargeDays = 0;
            for(int i = 0; i<request.rentalDays(); i++){
                cal.add(Calendar.DATE, 1);
                if((toolCharge.getWeekendCharge() && cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
                || (toolCharge.getWeekendCharge() && cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
                || toolCharge.getHolidayCharge() && (cal.get(Calendar.MONTH) == Calendar.SEPTEMBER && cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) // Labor day holiday
                || toolCharge.getHolidayCharge() && (cal.get(Calendar.MONTH) == Calendar.JULY && cal.get(Calendar.DATE) == 4) // Fourth of July
                || !(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY))
                {
                    chargeDays++;
                }
                if((!toolCharge.getHolidayCharge() && (cal.get(Calendar.MONTH) == Calendar.JULY && cal.get(Calendar.DATE) == 4))
                    && (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
                ){
                    chargeDays--; // If it's july 4th and the day is a weekend, we deduct one day from charge days
                }
            }
            rental.setDueDate(cal.getTime());
            rental.setChargeDays(chargeDays);
            double temp = chargeDays*toolCharge.getDailyCharge();
            double rounded = Math.ceil(temp * 2) / 2;
            rental.setPreDiscountCharge(rounded);
            temp = (rental.getPreDiscountCharge()* request.discountPercent())/100;
            rounded = Math.ceil(temp * 2) / 2;
            rental.setDiscountAmount(rounded);
            rental.setFinalCharge(rental.getPreDiscountCharge() - rental.getDiscountAmount());

            rental = rentalRepository.save(rental);

        }catch (IllegalArgumentException exp){
            exp.printStackTrace();
        }
        return rental;
    }
}
