package com.Joeyshi.landon.web.application;


import com.Joeyshi.landon.business.domain.RoomReservation;
import com.Joeyshi.landon.business.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequestMapping(value="/reservations")
public class ReservationController {


    @Autowired
    private ReservationService reservationService;

    @RequestMapping(method= RequestMethod.GET)
    public String getReservation(@RequestParam(value="date",required=false)String dateString, Model model){



        List<RoomReservation> roomReservationsList = this.reservationService.getRoomReservationsForDate(dateString);
        model.addAttribute("roomReservations",roomReservationsList);

        //why it's return below string, but the data are all store in model, tag "roomReservations"?
        return "reservations";
    }


}
