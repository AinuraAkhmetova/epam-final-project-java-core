package homework_7.carrier.repo;

import homework_7.carrier.domain.Carrier;
import homework_7.common.business.repo.CommonRepo;

public interface CarrierRepo extends CommonRepo {

  void add(Carrier carrier);

  Carrier getById(long id);

  Carrier[] getByName(String name);

}
