package homework_9.carrier.service;

import homework_9.carrier.domain.Carrier;
import homework_9.common.business.service.CommonService;

import java.util.List;

public interface CarrierService extends CommonService {
  void add(Carrier carrier);

  Carrier getById(Long id);

  List<Carrier> findByName(String name);

  List<Carrier> getAll();

  void update(Carrier carrier);

}
