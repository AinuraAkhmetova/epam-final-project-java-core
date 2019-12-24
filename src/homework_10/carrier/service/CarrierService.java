package homework_10.carrier.service;

import homework_10.carrier.domain.Carrier;
import homework_10.common.business.service.CommonService;

import java.util.List;

public interface CarrierService extends CommonService {
  void add(Carrier carrier);

  Carrier getById(Long id);

  Carrier getByIdFetchingTransportations(Long id);

  List<Carrier> findByName(String name);

  List<Carrier> getAll();

  void update(Carrier carrier);

}
