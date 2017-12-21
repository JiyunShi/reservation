package com.Joeyshi.landon.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.Joeyshi.landon.data.entity.Guest;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends PagingAndSortingRepository<Guest, Long> {

}