package homework_8.transportation.repo;

import homework_8.common.business.repo.CommonRepo;
import homework_8.transportation.domain.Transportation;

import java.util.List;

public interface TransportationRepo extends CommonRepo {
    void add(Transportation transportation);

    Transportation getById(long id);

    List<Transportation> getAll();
}
