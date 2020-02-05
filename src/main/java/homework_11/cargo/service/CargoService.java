package homework_11.cargo.service;

import homework_11.cargo.domain.Cargo;
import homework_11.cargo.search.CargoSearchCondition;
import homework_11.common.business.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService<Cargo, Long> {

    Cargo getByIdFetchingTransportations(Long id);

    List<Cargo> findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);
}
