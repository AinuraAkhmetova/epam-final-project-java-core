package homework_7.transportation.repo;

import homework_7.common.business.repo.CommonRepo;
import homework_7.transportation.domain.Transportation;

public interface TransportationRepo extends CommonRepo {
    void add(Transportation transportation);

    Transportation getById(long id);
}
