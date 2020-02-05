package homework_13.cargo.service;

import homework_13.cargo.domain.Cargo;
import homework_13.cargo.search.CargoSearchCondition;
import homework_13.common.business.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService<Cargo, Long> {

    Cargo getByIdFetchingTransportations(Long id);

    List<Cargo> findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
