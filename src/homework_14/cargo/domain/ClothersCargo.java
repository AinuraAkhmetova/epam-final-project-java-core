package homework_14.cargo.domain;

import java.io.Serializable;

public class ClothersCargo extends Cargo implements Serializable {

  private String size;
  private String material;

  @Override
  public CargoType getCargoType() {
    return CargoType.CLOTHERS;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public String getMaterial() {
    return material;
  }

  public void setMaterial(String material) {
    this.material = material;
  }


}
