package homework_7.cargo.repo;

import homework_7.cargo.domain.Cargo;
import homework_7.common.business.repo.CommonRepo;

public interface CargoArrayRepo extends CommonRepo, CargoCommonRepo {

  Cargo[] getByName(String name);

}
