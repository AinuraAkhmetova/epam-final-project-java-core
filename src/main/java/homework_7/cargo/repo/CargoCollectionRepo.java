package homework_7.cargo.repo;

import homework_7.cargo.domain.Cargo;
import homework_7.common.business.repo.CommonRepo;
import java.util.List;

public interface CargoCollectionRepo extends CommonRepo,CargoCommonRepo {

    List<Cargo> getByName(String name);

}
