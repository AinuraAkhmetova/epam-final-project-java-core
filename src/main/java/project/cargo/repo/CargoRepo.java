package project.cargo.repo;

import project.cargo.domain.Cargo;
import project.cargo.search.CargoSearchCondition;
import project.common.business.repo.CommonRepo;

import java.util.List;
import java.util.Optional;

public interface CargoRepo extends CommonRepo<Cargo, Long> {

  Optional<Cargo> getByIdFetchingTransportations(long id);

  Cargo[] findByName(String name);

  List<Cargo> search(CargoSearchCondition cargoSearchCondition);
}
