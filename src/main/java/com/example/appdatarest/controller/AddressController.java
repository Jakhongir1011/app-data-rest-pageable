package com.example.appdatarest.controller;


import com.example.appdatarest.entity.Address;
import com.example.appdatarest.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping
    public HttpEntity<?> getAddresses(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        List<Address> result = addressService.getAddressService(page,size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getAddress(@PathVariable Integer id){
        Address address = addressService.getByIdAddressService(id);
        return ResponseEntity.status(address!=null ? 201:409).body(address);
    }

    @PostMapping
    public HttpEntity<?> addedAddress(@RequestBody Address address){
        Address addedAddressService = addressService.addedAddressService(address);
        return ResponseEntity.status(201).body(addedAddressService);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> editAddress(@PathVariable Integer id, @RequestBody Address address){
        Address editAddress = addressService.editAddressService(id,address);
        return ResponseEntity.status(editAddress != null ? 202:409).body(editAddress);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteAddress(@PathVariable Integer id){
        boolean deleted = addressService.deleteAddressService(id);
        if (deleted)
            return ResponseEntity.noContent().build();// noContent 204 qaytaradi.
        return ResponseEntity.notFound().build(); // notFound 400 qaytaradi. Ne'ga bunday qildik bodyga hech narsa qatrmasligimiz uchun
    }
}
