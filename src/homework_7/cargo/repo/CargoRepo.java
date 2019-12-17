package homework_7.cargo.repo;

import homework_7.cargo.domain.Cargo;
import homework_7.common.business.repo.CommonRepo;

public interface CargoRepo extends CommonRepo {

  void add(Cargo cargo);

  Cargo getById(long id);

  Cargo[] getByName(String name);
}
