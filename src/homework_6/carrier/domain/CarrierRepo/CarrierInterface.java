package homework_6.carrier.domain.CarrierRepo;

import homework_6.carrier.domain.Carrier;

public interface CarrierInterface {
    Carrier getCarrierById(long id);
    Carrier[] getCarrierByName(String name);
    void addCarrier(Carrier carrier);
    void deleteCarrier(long id);
    void printAllCarriers();
}
