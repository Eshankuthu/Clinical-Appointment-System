package Helper;
  public class Choice {
    String id; String displayString;
    public Choice(String id)                       { this(id, id); }
   // public Choice(String  displayString)            { this(null, displayString); }
    public Choice(String id, String displayString) { this.id = id; this.displayString = displayString; }
    @Override public String toString() { return displayString; }
     public String getId() { return id; }
    @Override public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Choice choice = (Choice) o;
      return displayString != null && displayString.equals(choice.displayString) || id != null && id.equals(choice.id);
    }
    @Override public int hashCode() {
      int result = id != null ? id.hashCode() : 0;
      result = 31 * result + (displayString != null ? displayString.hashCode() : 0);
      return result;
    }
  }