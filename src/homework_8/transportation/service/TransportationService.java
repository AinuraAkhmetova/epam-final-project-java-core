package homework_8.transportation.service;

import homework_8.common.business.service.CommonService;
import homework_8.transportation.domain.Transportation;

import java.util.List;

public interface TransportationService extends CommonService {
    void add(Transportation transportation);

    Transportation getById(Long id);

    List<Transportation> getAll();

    void updateDescription(Long id, String description);

    void updateBillTo(Long id, String billTo);


}
