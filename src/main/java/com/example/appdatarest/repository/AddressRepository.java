package com.example.appdatarest.repository;

import com.example.appdatarest.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {


}
