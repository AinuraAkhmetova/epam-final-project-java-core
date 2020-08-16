package project.cargo.repo.impl;

import project.cargo.domain.Cargo;
import project.cargo.domain.CargoType;
import project.cargo.domain.ClothersCargo;
import project.cargo.domain.FoodCargo;

import java.sql.ResultSet;

public class CargoMapper {

    public static Cargo mapCargo(ResultSet rs) throws Exception {
        Cargo cargo;
        String type = rs.getString("ENTITY_TYPE");
        if (type.equals(CargoType.FOOD.name())) {
            FoodCargo foodCargo = new FoodCargo();
            foodCargo.setExpirationDate(rs.getTimestamp("FOOD_EXPIRATION_DATE"));
            foodCargo.setStoreTemperature(rs.getInt("FOOD_STORE_TEMPERATURE"));
            cargo = foodCargo;

        } else {
            ClothersCargo clothersCargo = new ClothersCargo();
            clothersCargo.setSize(rs.getString("CLOTHERS_SIZE"));
            clothersCargo.setMaterial(rs.getString("CLOTHERS_MATERIAL"));
            cargo = clothersCargo;
        }
        cargo.setId(rs.getLong("ID"));
        cargo.setName(rs.getString("NAME"));
        cargo.setWeight(rs.getInt("WEIGHT"));
        return cargo;
    }
}
