package homework_22.cargo.repo;

import homework_22.cargo.domain.Cargo;
import homework_22.cargo.search.CargoSearchCondition;
import homework_22.common.business.repo.CommonRepo;

import java.util.List;
import java.util.Optional;

public interface CargoRepo extends CommonRepo<Cargo, Long> {

  Optional<Cargo> getByIdFetchingTransportations(long id);

  Cargo[] findByName(String name);

  List<Cargo> search(CargoSearchCondition cargoSearchCondition);
}
