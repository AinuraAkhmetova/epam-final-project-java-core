package homework_7.cargo.service;

import homework_7.cargo.domain.Cargo;
import homework_7.common.business.service.CommonService;

public interface CargoService extends CommonService {
    void add(Cargo cargo);

    Cargo getById(Long id);
}
