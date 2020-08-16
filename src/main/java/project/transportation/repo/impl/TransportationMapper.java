package project.transportation.repo.impl;

import project.cargo.repo.impl.CargoDataBaseRepoImpl;
import project.transportation.domain.Transportation;

import java.sql.ResultSet;


public class TransportationMapper {

    public static Transportation mapTransportation(ResultSet rs) throws Exception {
        CargoDataBaseRepoImpl cargoDataBaseRepo = new CargoDataBaseRepoImpl();

        Transportation transportation = new Transportation();
        transportation.setId(rs.getLong("ID"));


        transportation.setCarrier(null);

        transportation.setDescription(rs.getString("DESCRIPTION"));
        transportation.setBillTo(rs.getString("BILL_TO"));
        transportation.setTransportationBeginDate(rs.getTimestamp("BEGIN_DATE"));

        Long cargoId = rs.getLong("CARGO_ID");


        System.out.println(cargoDataBaseRepo.findById(cargoId).toString());

        //transportation.setCargo(cargo.get());
        return transportation;
    }

}
