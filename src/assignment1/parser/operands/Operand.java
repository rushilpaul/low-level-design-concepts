package assignment1.parser.operands;

import java.util.Objects;

public abstract class Operand implements Expression {

   private Object value;

   public Operand() {
      value = null;
   }

   public Operand(Object value) {
      this.value = value;
   }

   public Object getValue() {
      return value;
   }

   public void setValue(Object value) {
      this.value = value;
   }

   public abstract DataType getDataType();

   @Override
   public Operand evaluate() {
      return this;
   }

   @Override
   public String toString() {
      return String.format("[%s : %s]", value, getDataType());
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null) return false;
      Operand operand = (Operand) o;
      return value.toString().equals(operand.value.toString());
   }

   @Override
   public int hashCode() {
      return Objects.hash(value);
   }
}
