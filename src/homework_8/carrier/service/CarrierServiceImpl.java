package homework_8.carrier.service;

import homework_8.carrier.domain.Carrier;
import homework_8.carrier.repo.CarrierRepo;
import homework_8.transportation.domain.Transportation;

import java.util.Arrays;
import java.util.Collections;

import java.util.List;

public class CarrierServiceImpl implements CarrierService {

    private CarrierRepo carrierRepo;

    public CarrierServiceImpl(
            CarrierRepo carrierRepo) {
        this.carrierRepo = carrierRepo;
    }

    @Override
    public void add(Carrier carrier) {
        carrierRepo.add(carrier);
    }

    @Override
    public Carrier getById(Long id) {
        if (id != null) {
            return carrierRepo.getById(id);
        }
        return null;
    }

    @Override
    public List<Carrier> getByName(String name) {
        Carrier[] found = carrierRepo.getByName(name);
        return (found == null || found.length == 0) ? Collections.emptyList() : Arrays.asList(found);

    }

    @Override
    public List<Carrier> getAll() {
        return carrierRepo.getAll();
    }


    @Override
    public boolean deleteById(Long id) {
        return carrierRepo.deleteById(id);
    }

    @Override
    public void printAll() {
        List<Carrier> carriers = carrierRepo.getAll();
        for (Carrier carrier : carriers) {
            System.out.println(carrier);
        }
    }


    @Override
    public void updateTransportation(Long id, Transportation[] transportation) {
        if (carrierRepo.getById(id) != null) {
            carrierRepo.getById(id).setTransportations(transportation);
        }
    }

    @Override
    public void updateName(Long id, String name) {
        if (carrierRepo.getById(id) != null) {
            carrierRepo.getById(id).setName(name);
        }
    }

    @Override
    public void updateAddress(Long id, String address) {
        if (carrierRepo.getById(id) != null) {
            carrierRepo.getById(id).setAddress(address);
        }
    }

}
