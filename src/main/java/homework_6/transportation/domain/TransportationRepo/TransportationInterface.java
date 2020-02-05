package homework_6.transportation.domain.TransportationRepo;

import homework_6.transportation.domain.Transportation;

public interface TransportationInterface {
    void addTransportation(Transportation transportation);
    Transportation getTransportationById(long id);
    void printAllTransportations();


}
