package homework_9.transportation.repo;

import homework_9.common.business.repo.CommonRepo;
import homework_9.transportation.domain.Transportation;

import java.util.List;

public interface TransportationRepo extends CommonRepo {
    void add(Transportation transportation);

    Transportation getById(long id);

    List<Transportation> getAll();

    void update(Transportation transportation);
}
