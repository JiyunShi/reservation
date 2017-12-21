package com.Joeyshi.landon.web.application;


import com.Joeyshi.landon.business.domain.RoomReservation;
import com.Joeyshi.landon.business.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value="/reservations")
public class ReservationController {

    private static final DateFormat DATE_FORMAT=new SimpleDateFormat("yyyy-MM-DD");
    @Autowired
    private ReservationService reservationService;

    @RequestMapping(method= RequestMethod.GET)
    public String getReservation(@RequestParam(value="date",required=false)String dateString, Model model){


        Date date = null;
        if(null!=dateString){
            try{
                date=DATE_FORMAT.parse(dateString);
            }catch (ParseException pe){
                date = new Date();
            }
        }else{
            date = new Date();
        }
        List<RoomReservation> roomReservationsList = this.reservationService.getRoomReservationsForDate(date);
        model.addAttribute("roomReservations",roomReservationsList);

        //why it's return below string, but the data are all store in model, tag "roomReservations"?
        return "reservations";
    }


}
