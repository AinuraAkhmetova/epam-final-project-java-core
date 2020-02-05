package homework_7.carrier.repo;

import homework_7.carrier.domain.Carrier;
import homework_7.common.business.repo.CommonRepo;

public interface CarrierArrayRepo extends CommonRepo, CarrierCommonRepo {
    Carrier[] getByName(String name);
}
