package homework_12.storage.initor;

import homework_12.cargo.service.CargoService;
import homework_12.carrier.service.CarrierService;
import homework_12.transportation.service.TransportationService;
import homework_12.cargo.domain.ClothersCargo;
import homework_12.cargo.domain.FoodCargo;
import homework_12.carrier.domain.Carrier;
import homework_12.transportation.domain.Transportation;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;


public class FromXMLStorageInitor implements StorageInitor {

    private CarrierService carrierService;
    private CargoService cargoService;
    private TransportationService transportationService;

    private static final String CARGO = "cargo";
    private static final String CARRIER = "carrier";
    private static final String TRANSPORTATION = "transportation";

    @Override
    public void initStorage() throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("D:\\Рабочий стол\\epam_homeworks\\resources\\homework_12\\logistics-company.xml"));

        collectInformation(document, CARGO);
        collectInformation(document, CARRIER);
        collectInformation(document, TRANSPORTATION);

    }

    public FromXMLStorageInitor() {

        carrierService = homework_12.application.serviceholder.ServiceHolder.getInstance().getCarrierService();
        cargoService = homework_12.application.serviceholder.ServiceHolder.getInstance().getCargoService();
        transportationService = homework_12.application.serviceholder.ServiceHolder.getInstance().getTransportationService();
    }


    public void collectInformation(Document document, final String element) {
        NodeList elements = document.getElementsByTagName(element);
        for (int i = 0; i < elements.getLength(); i++) {

            NamedNodeMap attributes = elements.item(i).getAttributes();
            switch (element) {
                case CARGO: {

                    if (attributes.getNamedItem("type").getNodeValue().equals("CLOTHES")) {
                        ClothersCargo clothersCargo = new ClothersCargo();
                        clothersCargo.setName(attributes.getNamedItem("name").getNodeValue());
                        clothersCargo.setWeight(Integer.parseInt(attributes.getNamedItem("weight").getNodeValue()));
                        clothersCargo.setSize(attributes.getNamedItem("size").getNodeValue());
                        clothersCargo.setMaterial(attributes.getNamedItem("material").getNodeValue());
                        cargoService.save(clothersCargo);
                    }
                    if (attributes.getNamedItem("type").getNodeValue().equals("FOOD")) {
                        FoodCargo foodCargo = new FoodCargo();
                        foodCargo.setName(attributes.getNamedItem("name").getNodeValue());
                        foodCargo.setWeight(Integer.parseInt(attributes.getNamedItem("weight").getNodeValue()));
                        foodCargo.setStoreTemperature(Integer.parseInt(attributes.getNamedItem("storeTemperature").getNodeValue()));
                        cargoService.save(foodCargo);
                    }
                    break;
                }
                case CARRIER: {
                    Carrier carrier = new Carrier();
                    carrier.setName(attributes.getNamedItem("name").getNodeValue());
                    carrier.setAddress(attributes.getNamedItem("address").getNodeValue());
                    carrierService.save(carrier);
                }
                break;

                case TRANSPORTATION: {
                    Transportation transportation = new Transportation();
                    transportation.setBillTo(attributes.getNamedItem("billTo").getNodeValue());
                    transportation.setDescription(attributes.getNamedItem("description").getNodeValue());
                    transportationService.save(transportation);
                }
                break;
            }
        }
    }

}




