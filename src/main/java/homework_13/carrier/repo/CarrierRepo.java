package homework_13.carrier.repo;

import homework_13.carrier.domain.Carrier;
import homework_13.common.business.repo.CommonRepo;

public interface CarrierRepo extends CommonRepo<Carrier, Long> {

  Carrier getByIdFetchingTransportations(long id);

  Carrier[] findByName(String name);

}
