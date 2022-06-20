package com.example.appdatarest.service;

import com.example.appdatarest.entity.Address;
import com.example.appdatarest.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;
    public List<Address> getAddressService(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Address> all = addressRepository.findAll(pageable);
        return  all.getContent(); // sahifani ichidan 10 ta ma'lumot olib beradi
    }

    public Address getByIdAddressService(Integer id) {
        Optional<Address> byId = addressRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return null;
    }

    public Address addedAddressService(Address address) {
        Address save = addressRepository.save(address);
        return save;
    }

    public Address editAddressService(Integer id, Address address) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent()){
           return  null;
        }
        Address addressOptional = optionalAddress.get();
        addressOptional.setCity(address.getCity());
        addressOptional.setDistrict(address.getDistrict());
        addressOptional.setStreet(address.getStreet());
        Address save = addressRepository.save(addressOptional);
        return save;
    }

    public boolean deleteAddressService(Integer id) {
        try {
            addressRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
