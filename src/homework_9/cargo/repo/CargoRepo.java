package homework_9.cargo.repo;

import homework_9.cargo.domain.Cargo;
import homework_9.cargo.search.CargoSearchCondition;
import homework_9.common.business.repo.CommonRepo;

import java.util.List;

public interface CargoRepo extends CommonRepo {

  void add(Cargo cargo);

  Cargo getById(long id);

  Cargo[] findByName(String name);

  List<Cargo> getAll();

  List<Cargo> search(CargoSearchCondition cargoSearchCondition);

  void update(Cargo cargo);
}
