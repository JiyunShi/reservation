package com.Joeyshi.landon.business.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Joeyshi.landon.business.domain.RoomReservation;
import com.Joeyshi.landon.data.entity.Guest;
import com.Joeyshi.landon.data.entity.Reservation;
import com.Joeyshi.landon.data.entity.Room;
import com.Joeyshi.landon.data.repository.GuestRepository;
import com.Joeyshi.landon.data.repository.ReservationRepository;
import com.Joeyshi.landon.data.repository.RoomRepository;

@Service
public class ReservationService {
	
	private RoomRepository roomRepository;
	private GuestRepository guestRepository;
	private ReservationRepository reservationRepository;
	
	
	@Autowired
	public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository,
			ReservationRepository reservationRepository) {
		
		this.roomRepository = roomRepository;
		this.guestRepository = guestRepository;
		this.reservationRepository = reservationRepository;
	}
	
	public List<RoomReservation> getRoomReservationsForDate(Date date){
		Iterable<Room> rooms = this.roomRepository.findAll();
		Map<Long, RoomReservation> roomReservationMap=new HashMap<>();
		rooms.forEach(room->{
			RoomReservation roomReservation = new RoomReservation();
			roomReservation.setRoomId(room.getId());
			roomReservation.setRoomName(room.getName());
			roomReservation.setRoomNumber(room.getNumber());
			roomReservationMap.put(room.getId(),roomReservation);
		});
		
		Iterable<Reservation> reservations = this.reservationRepository.findByDate(new java.sql.Date(date.getTime()) );
		if(null!=reservations){
			reservations.forEach(reservation->{
				Guest guest = this.guestRepository.findOne(reservation.getGuestId());
				if(null!=guest){
					RoomReservation roomReservation = roomReservationMap.get(reservation.getId());
					roomReservation.setGuestId(guest.getId());
					roomReservation.setDate(date);
					roomReservation.setFirstName(guest.getFirstName());
					roomReservation.setLastName(guest.getLastName());
					
				}
			});
		}
		List<RoomReservation> roomReservations = new ArrayList<>();
		for(Long roomId: roomReservationMap.keySet()){
			roomReservations.add(roomReservationMap.get(roomId));
		}
		
		return roomReservations;
	}
	
	
	
	
	

}
