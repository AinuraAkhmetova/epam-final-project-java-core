package homework_10.transportation.repo;

import homework_10.common.business.repo.CommonRepo;
import homework_10.transportation.domain.Transportation;

import java.util.List;

public interface TransportationRepo extends CommonRepo {
    void add(Transportation transportation);

    Transportation getById(long id);

    List<Transportation> getAll();

    void update(Transportation transportation);
}
