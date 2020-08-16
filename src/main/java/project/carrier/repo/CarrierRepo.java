package project.carrier.repo;

import project.carrier.domain.Carrier;
import project.common.business.repo.CommonRepo;

import java.util.Optional;

public interface CarrierRepo extends CommonRepo<Carrier, Long> {

  Optional<Carrier> getByIdFetchingTransportations(long id);

  Carrier[] findByName(String name);

}
