package homework_8.transportation.service;

import homework_8.transportation.domain.Transportation;
import homework_8.transportation.repo.TransportationRepo;

import java.util.List;

public class TransportationServiceImpl implements TransportationService {

    private TransportationRepo transportationRepo;

    public TransportationServiceImpl(
            TransportationRepo transportationRepo) {
        this.transportationRepo = transportationRepo;
    }

    @Override
    public boolean deleteById(Long id) {
        return transportationRepo.deleteById(id);
    }

    @Override
    public void printAll() {
        List<Transportation> allTransportations = transportationRepo.getAll();
        for (Transportation transportation : allTransportations) {
            System.out.println(transportation);
        }
    }

    @Override
    public void add(Transportation transportation) {
        transportationRepo.add(transportation);
    }

    @Override
    public Transportation getById(Long id) {
        return transportationRepo.getById(id);
    }

    @Override
    public List<Transportation> getAll() {
        return transportationRepo.getAll();
    }

    @Override
    public void updateDescription(Long id, String description) {
        if (transportationRepo.getById(id) != null) {
            transportationRepo.getById(id).setDescription(description);
        }
    }
    @Override
    public void updateBillTo(Long id, String billTo) {
        if (transportationRepo.getById(id) != null) {
            transportationRepo.getById(id).setBillTo(billTo);
        }

    }
}
