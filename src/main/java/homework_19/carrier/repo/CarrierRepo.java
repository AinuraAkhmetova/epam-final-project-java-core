package homework_19.carrier.repo;

import homework_19.carrier.domain.Carrier;
import homework_19.common.business.repo.CommonRepo;

public interface CarrierRepo extends CommonRepo<Carrier, Long> {

  Carrier getByIdFetchingTransportations(long id);

  Carrier[] findByName(String name);

}
