package CAS;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public abstract class Service {
	private String serviceId;
	private int duration;
	private double cost;
	private String serviceName;

	public abstract DefaultTableModel getInfo();

	public Service() {
		DefaultTableModel tbl = getInfo();
		setServiceId(tbl.getValueAt(0, 0).toString());
		setServiceName(tbl.getValueAt(0, 1).toString());
		setCost((double) tbl.getValueAt(0, 2));
		setDuration((int) tbl.getValueAt(0, 3));
	}
	public static Service getService(String serviceId) {
		if(serviceId=="1")
			return new Consultation();
		else 
			return new Treatment();
			
	}
	public static List<Service> getServiceList() {
		List<Service> s = new ArrayList<>();
		s.add(new Consultation());
		s.add(new Treatment());
		return s;
	}


	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public void setServiceId(String serviceId) {

		this.serviceId = serviceId;
	}

	public String getServiceId() {

		return serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
}
