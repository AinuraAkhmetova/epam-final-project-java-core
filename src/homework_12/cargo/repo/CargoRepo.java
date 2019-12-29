package homework_12.cargo.repo;

import homework_12.cargo.domain.Cargo;
import homework_12.cargo.search.CargoSearchCondition;
import homework_12.common.business.repo.CommonRepo;

import java.util.List;

public interface CargoRepo extends CommonRepo<Cargo, Long> {

  Cargo getByIdFetchingTransportations(long id);

  Cargo[] findByName(String name);

  List<Cargo> search(CargoSearchCondition cargoSearchCondition);
}
