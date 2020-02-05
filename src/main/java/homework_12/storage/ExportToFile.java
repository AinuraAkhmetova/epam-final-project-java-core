package homework_12.storage;

import homework_12.application.serviceholder.ServiceHolder;
import homework_12.cargo.domain.Cargo;
import homework_12.cargo.domain.CargoType;
import homework_12.cargo.domain.ClothersCargo;
import homework_12.cargo.domain.FoodCargo;
import homework_12.carrier.domain.Carrier;
import homework_12.transportation.domain.Transportation;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class ExportToFile {
    private static final String FILE_PATH = "D:\\Рабочий стол\\epam_homeworks\\resources\\homework_12\\data-from-storage.txt";

    public void writeToFile() {
        try (
                PrintWriter writer = new PrintWriter(
                        new FileWriter(new File(FILE_PATH))
                )
        ) {
            List<Cargo> cargos = ServiceHolder.getInstance().getCargoService().getAll();
            writeCargos(writer, cargos);

            List<Carrier> carriers = ServiceHolder.getInstance().getCarrierService().getAll();
            writeCarriers(writer, carriers);

            List<Transportation> transportations = ServiceHolder.getInstance().getTransportationService().getAll();
            writeTransportations(writer, transportations);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeCargos(PrintWriter writer, List<Cargo> cargos) {
        writer.write("---Cargo section--" + "\n");
        for (Cargo cargo : cargos) {
            writer.write(cargo.getId().toString() + " | ");
            writer.write(cargo.getCargoType().toString() + " | ");
            writer.write(cargo.getName() + " | ");
            writer.write(cargo.getWeight() + " | ");

            if (cargo.getCargoType().equals(CargoType.CLOTHERS)) {
                writer.write(((ClothersCargo) cargo).getSize() + " | ");
                writer.write(((ClothersCargo) cargo).getMaterial() + "\n");
            } else {
                writer.write(((FoodCargo) cargo).getExpirationDate().toString() + " | ");
                writer.write(((FoodCargo) cargo).getStoreTemperature() + "\n");
            }
        }
    }

    public static void writeCarriers(PrintWriter writer, List<Carrier> carriers) {
        writer.write("\n" + "---Carrier section--" + "\n");
        for (Carrier carrier : carriers) {
            writer.write(carrier.getId().toString() + " | ");
            writer.write(carrier.getName() + " | ");
            writer.write(carrier.getAddress() + " | ");
            writer.write(carrier.getCarrierType() + "\n");
        }
    }

    public static void writeTransportations(PrintWriter writer, List<Transportation> transportations) {
        writer.write("\n" + "---Transportation section--" + "\n");
        for (Transportation transportation : transportations) {
            writer.write(transportation.getCargo().getId() + " | ");
            writer.write(transportation.getCarrier().getId() + " | ");
            writer.write(transportation.getDescription() + " | ");
            writer.write(transportation.getBillTo() + " | ");
            writer.write(transportation.getTransportationBeginDate() + "\n");
        }
    }
}


