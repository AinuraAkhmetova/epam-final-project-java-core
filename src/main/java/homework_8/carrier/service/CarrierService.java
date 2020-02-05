package homework_8.carrier.service;

import homework_8.carrier.domain.Carrier;
import homework_8.common.business.service.CommonService;
import homework_8.transportation.domain.Transportation;

import java.util.List;

public interface CarrierService extends CommonService {
    void add(Carrier carrier);

    Carrier getById(Long id);

    List<Carrier> getByName(String name);

    List<Carrier> getAll();

    void updateAddress(Long id, String address);

    void updateTransportation(Long id, Transportation transportation[]);

    void updateName(Long id, String name);

}
