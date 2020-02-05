package homework_9.transportation.service;

import homework_9.common.business.service.CommonService;
import homework_9.transportation.domain.Transportation;

import java.util.List;

public interface TransportationService extends CommonService {
  void add(Transportation transportation);

  Transportation getById(Long id);

  List<Transportation> getAll();

  void update(Transportation transportation);

}
