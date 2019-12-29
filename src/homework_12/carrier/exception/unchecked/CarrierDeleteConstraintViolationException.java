package homework_12.carrier.exception.unchecked;

import homework_12.common.business.exception.unchecked.OurCompanyUncheckedException;

public class CarrierDeleteConstraintViolationException extends OurCompanyUncheckedException {

  private static final String MESSAGE = "Cant delete carrier with id '%s'. There are transportations which relates to it!";

  public CarrierDeleteConstraintViolationException(String message) {
    super(message);
  }

  public CarrierDeleteConstraintViolationException(long carrierId) {
    this(String.format(MESSAGE, carrierId));
  }
}
