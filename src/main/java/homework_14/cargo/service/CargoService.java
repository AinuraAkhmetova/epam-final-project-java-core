package homework_14.cargo.service;

import homework_14.cargo.domain.Cargo;
import homework_14.cargo.search.CargoSearchCondition;
import homework_14.common.business.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService<Cargo, Long> {

    Cargo getByIdFetchingTransportations(Long id);

    List<Cargo> findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
