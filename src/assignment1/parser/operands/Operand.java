package assignment1.parser.operands;

import assignment1.parser.DataType;

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

   abstract public DataType getDataType();

}
