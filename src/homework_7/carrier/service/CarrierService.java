package homework_7.carrier.service;

import homework_7.carrier.domain.Carrier;
import homework_7.common.business.service.CommonService;

public interface CarrierService extends CommonService {
    void add(Carrier cargo);

    Carrier getById(Long id);

}
