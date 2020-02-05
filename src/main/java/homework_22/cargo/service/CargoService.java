package homework_22.cargo.service;

import homework_22.cargo.domain.Cargo;
import homework_22.cargo.search.CargoSearchCondition;
import homework_22.common.business.service.CommonService;

import java.util.List;
import java.util.Optional;

public interface CargoService extends CommonService<Cargo, Long> {

    Optional<Cargo> getByIdFetchingTransportations(Long id);

    List<Cargo> findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
