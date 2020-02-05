package homework_7.carrier.repo;

import homework_7.carrier.domain.Carrier;
import homework_7.common.business.repo.CommonRepo;

import java.util.List;

public interface CarrierCollectionRepo extends CommonRepo,CarrierCommonRepo {
    List<Carrier> getByName(String name);
}
