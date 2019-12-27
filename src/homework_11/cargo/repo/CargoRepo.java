package homework_11.cargo.repo;

import homework_11.cargo.domain.Cargo;
import homework_11.cargo.search.CargoSearchCondition;
import homework_11.common.business.repo.CommonRepo;

import java.util.List;

public interface CargoRepo extends CommonRepo<Cargo, Long> {

  Cargo getByIdFetchingTransportations(long id);

  Cargo[] findByName(String name);

  List<Cargo> search(CargoSearchCondition cargoSearchCondition);
}
