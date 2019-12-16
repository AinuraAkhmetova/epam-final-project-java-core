package homework_6.cargo.domain;

import homework_6.cargo.domain.CargoRepo.CargoInterface;
import homework_6.common.utils.ArrayUtils;
import homework_6.storage.IdGenerator;
import homework_6.transportation.domain.Transportation;
import homework_6.common.domain.BaseEntity;

import java.util.Arrays;
import java.util.Objects;

public abstract class Cargo extends BaseEntity {

  private String name;
  private int weight;
  private Transportation[] transportations;
  private CargoType cargoType;


  public Cargo() {
    cargoType = this.getCargoType();
  }

  public abstract CargoType getCargoType();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  public Transportation[] getTransportations() {
    return transportations;
  }

  public void setTransportations(Transportation[] transportations) {
    this.transportations = transportations;
  }

  @Override
  public String toString() {
    return "Cargo{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", weight=" + weight +
        ", cargoType=" + cargoType +
        ", transportations=" + Arrays.toString(transportations) +
        '}';
  }
}
