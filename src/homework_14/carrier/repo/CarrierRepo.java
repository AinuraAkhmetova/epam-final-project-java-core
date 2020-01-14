package homework_14.carrier.repo;

import homework_14.carrier.domain.Carrier;
import homework_14.common.business.repo.CommonRepo;

public interface CarrierRepo extends CommonRepo<Carrier, Long> {

  Carrier getByIdFetchingTransportations(long id);

  Carrier[] findByName(String name);

}
