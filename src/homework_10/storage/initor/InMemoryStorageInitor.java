package homework_10.storage.initor;

import static homework_10.common.solutions.utils.CollectionUtils.isNotEmpty;

import homework_10.application.serviceholder.ServiceHolder;
import homework_10.cargo.domain.Cargo;
import homework_10.cargo.domain.ClothersCargo;
import homework_10.cargo.domain.FoodCargo;
import homework_10.cargo.service.CargoService;
import homework_10.carrier.domain.Carrier;
import homework_10.carrier.service.CarrierService;
import homework_10.common.solutions.utils.CollectionUtils;
import homework_10.transportation.domain.Transportation;
import homework_10.transportation.service.TransportationService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class InMemoryStorageInitor implements StorageInitor {

  private static final int TOTAL_ENTITIES_IN_GROUP = 6;

  private final CarrierService carrierService;
  private final CargoService cargoService;
  private final TransportationService transportationService;

  public InMemoryStorageInitor() {
    carrierService = ServiceHolder.getInstance().getCarrierService();
    cargoService = ServiceHolder.getInstance().getCargoService();
    transportationService = ServiceHolder.getInstance().getTransportationService();
  }

  @Override
  public void initStorage() {
    initCargos();
    initCarriers();
    initTransportations();
    appendTransportationsToCargos();
  }

  private void initCargos() {
    for (int i = 0; i < TOTAL_ENTITIES_IN_GROUP / 2; i++) {
      cargoService.add(createClothersCargo(i));
    }
    for (int i = 0; i < TOTAL_ENTITIES_IN_GROUP / 2; i++) {
      cargoService.add(createFoodCargo(i));
    }
  }

  private ClothersCargo createClothersCargo(int index) {
    ClothersCargo cargo = new ClothersCargo();
    cargo.setSize("Clothers_Size_" + index);
    cargo.setName("Jeans");
    cargo.setWeight(ThreadLocalRandom.current().nextInt(1, 100 + 1));

    return cargo;
  }

  private FoodCargo createFoodCargo(int index) {
    FoodCargo cargo = new FoodCargo();
    cargo.setExpirationDate(new Date());
    cargo.setStoreTemperature(index);
    cargo.setWeight(ThreadLocalRandom.current().nextInt(1, 100 + 1));
    cargo.setName("Milk");

    return cargo;
  }

  private void initCarriers() {
    for (int i = 0; i < TOTAL_ENTITIES_IN_GROUP; i++) {
      Carrier carrier = createCarrier(i);
      carrierService.add(carrier);
    }
  }

  private Carrier createCarrier(int index) {
    Carrier carrier = new Carrier();
    carrier.setName("Carrier_Name");
    carrier.setAddress("Address_" + index);
    return carrier;
  }

  private void initTransportations() {
    for (int i = 0; i < TOTAL_ENTITIES_IN_GROUP; i++) {
      Transportation transportation = createTransportation(i + 1, i + 1 + TOTAL_ENTITIES_IN_GROUP);
      transportationService.add(transportation);
    }
  }

  private Transportation createTransportation(long cargoId, long carrierId) {
    Transportation transportation = new Transportation();
    transportation.setCargo(cargoService.getById(cargoId));
    transportation.setCarrier(carrierService.getById(carrierId));
    transportation.setDescription("Transportation");

    return transportation;
  }

  private void appendTransportationsToCargos() {
    List<Cargo> cargos = cargoService.getAll();
    List<Transportation> transportations = transportationService.getAll();

    if (isNotEmpty(cargos) && isNotEmpty(transportations)) {
      for (Cargo cargo : cargos) {
        appendTransportationsToCargo(cargo, transportations);
      }
    }
  }

  private void appendTransportationsToCargo(Cargo cargo,
      List<Transportation> transportations) {

    List<Transportation> cargoTransportations = cargo.getTransportations();
    if (cargoTransportations == null) {
      cargoTransportations = new ArrayList<>();
    }

    for (Transportation transportation : transportations) {
      if (transportation.getCargo() != null && transportation.getCargo().getId()
          .equals(cargo.getId())) {
        cargoTransportations.add(transportation);
      }
    }

    cargo.setTransportations(transportations);
  }
}