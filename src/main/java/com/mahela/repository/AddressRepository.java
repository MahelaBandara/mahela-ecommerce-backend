package com.mahela.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mahela.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
