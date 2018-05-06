package service;

import persistence.services.VehicleService;
import javax.ws.rs.Path;

@Path("/servicesVehicle")
public class VehicleRest {

    private VehicleService vehicleService;

    public VehicleService getVehicleService() {
        return vehicleService;
    }

    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

}
