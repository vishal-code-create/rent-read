package com.crio.rentread.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crio.rentread.entity.UserDetails;

public interface RentReadRepository extends JpaRepository<UserDetails, String> {

}