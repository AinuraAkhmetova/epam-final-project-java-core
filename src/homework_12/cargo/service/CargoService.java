package homework_12.cargo.service;

import homework_12.cargo.domain.Cargo;
import homework_12.cargo.search.CargoSearchCondition;
import homework_12.common.business.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService<Cargo, Long> {

    Cargo getByIdFetchingTransportations(Long id);

    List<Cargo> findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);
}
