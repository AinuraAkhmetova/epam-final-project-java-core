package homework_11.storage.initor;

import homework_11.application.serviceholder.ServiceHolder;
import homework_11.cargo.domain.Cargo;
import homework_11.cargo.domain.ClothersCargo;
import homework_11.cargo.domain.FoodCargo;
import homework_11.cargo.service.CargoService;
import homework_11.carrier.service.CarrierService;
import homework_11.transportation.domain.Transportation;
import homework_11.transportation.service.TransportationService;
import homework_11.carrier.domain.Carrier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static homework_11.common.solutions.utils.CollectionUtils.isNotEmpty;

public class InFileStorageInitor implements StorageInitor {
    private static final String FILE_PATH = "D:\\epam_homeworks\\Data.txt";

    private final CarrierService carrierService;
    private final CargoService cargoService;
    private final TransportationService transportationService;

    public InFileStorageInitor() {
        carrierService = ServiceHolder.getInstance().getCarrierService();
        cargoService = ServiceHolder.getInstance().getCargoService();
        transportationService = ServiceHolder.getInstance().getTransportationService();
    }

    @Override
    public void initStorage() {
        readFile();
        SetCargoAndCarrierToTransportation();
        appendTransportationsToCargos();
    }

    private void readFile() {
        File file = new File(FILE_PATH);
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(file)
        )) {
            String line;
            List<String> list = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }
            StringParser(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void StringParser(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("---CARGOS BEGIN---")) {
                i++;
                while (!list.get(i).equals("---CARGOS END---")) {
                    String[] strCargo = list.get(i).split(" | ");
                    if (strCargo[0].equals("MILK")) {
                        createFoodCargo(strCargo[0], Integer.parseInt(strCargo[2]));
                        i++;
                    } else if (strCargo[0].equals("JEANS")) {
                        createClothersCargo(strCargo[0], Integer.parseInt(strCargo[2]));
                        i++;
                    }
                }

            } else if (list.get(i).equals("---CARRIERS BEGIN---")) {
                i++;
                while (!list.get(i).equals("---CARRIERS END---")) {

                    String[] strCarrier = list.get(i).split(" | ");
                    createCarrier(strCarrier[0], strCarrier[2]);
                    i++;
                }
            } else if (list.get(i).equals("---TRANSPORTATIONS BEGIN---")) {
                i++;

                while (!list.get(i).equals("---TRANSPORTATIONS END---")) {
                    String[] strTransportation = list.get(i).split(" | ");
                    createTransportation(strTransportation[0], strTransportation[2]);
                    i++;
                }
            }
        }

    }

    private Transportation createTransportation(String description, String billTo) {
        Transportation transportation = new Transportation();
        transportation.setDescription(description);
        transportation.setBillTo(billTo);

        transportationService.save(transportation);

        return transportation;
    }

    private void SetCargoAndCarrierToTransportation() {
        int i = 0;
        for (Transportation transp : transportationService.getAll()) {
            i++;
            transp.setCargo(cargoService.findById((long) i));
            transp.setCarrier(carrierService.findById((long) i + cargoService.countAll()));

        }

    }

    private Carrier createCarrier(String name, String address) {
        Carrier carrier = new Carrier();
        carrier.setName(name);
        carrier.setAddress(address);
        carrierService.save(carrier);
        return carrier;
    }


    private FoodCargo createFoodCargo(String name, Integer weight) {
        FoodCargo cargo = new FoodCargo();
        cargo.setExpirationDate(new Date());
        cargo.setName(name);
        cargo.setWeight(weight);
        cargoService.save(cargo);
        return cargo;
    }

    private ClothersCargo createClothersCargo(String name, Integer weight) {
        ClothersCargo cargo = new ClothersCargo();
        cargo.setName(name);
        cargo.setWeight(weight);
        cargoService.save(cargo);
        return cargo;
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

    private void appendTransportationsToCargos() {
        List<Cargo> cargos = cargoService.getAll();
        List<Transportation> transportations = transportationService.getAll();

        if (isNotEmpty(cargos) && isNotEmpty(transportations)) {
            for (Cargo cargo : cargos) {
                appendTransportationsToCargo(cargo, transportations);
            }
        }
    }


}
