import java.util.Comparator;

public class NameSorter implements Comparator<Order>{
	    @Override
	    public int compare(Order o1, Order o2) {
	        return o1.getName().compareToIgnoreCase(o2.getName());
	    }
}
