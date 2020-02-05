package homework_9.carrier.repo;

import homework_9.carrier.domain.Carrier;
import homework_9.common.business.repo.CommonRepo;

import java.util.List;

public interface CarrierRepo extends CommonRepo {

  void add(Carrier carrier);

  Carrier getById(long id);

  Carrier[] findByName(String name);

  List<Carrier> getAll();

  void update(Carrier carrier);
}
