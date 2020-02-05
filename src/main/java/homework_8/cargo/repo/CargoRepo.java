package homework_8.cargo.repo;

import homework_8.cargo.domain.Cargo;
import homework_8.common.business.repo.CommonRepo;

import java.util.List;

public interface CargoRepo extends CommonRepo {

  void add(Cargo cargo);

  Cargo getById(long id);

  Cargo[] getByName(String name);

  List<Cargo> getAll();



}
