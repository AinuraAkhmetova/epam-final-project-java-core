package homework_13.carrier.service;

import homework_13.carrier.domain.Carrier;
import homework_13.common.business.service.CommonService;

import java.util.List;

public interface CarrierService extends CommonService<Carrier, Long> {

  Carrier getByIdFetchingTransportations(Long id);

  List<Carrier> findByName(String name);

}
