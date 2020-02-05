package homework_12.carrier.repo;

import homework_12.carrier.domain.Carrier;
import homework_12.common.business.repo.CommonRepo;

public interface CarrierRepo extends CommonRepo<Carrier, Long> {

  Carrier getByIdFetchingTransportations(long id);

  Carrier[] findByName(String name);

}
