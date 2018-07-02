package model;

import java.util.List;

public class MailSender{

    public static void sendStateAccountMail(List<Rental> ownerRentals, List<Rental> tenantRentals, User from){

        String body = " === Estado de Movimientos del mes ===\n\n"+
                "Crédito en tu cuenta: $"+from.getAccount().getCredit()+"\n\n";

        String ownerMov = "Momimientos de tus vehículos:\n";
        for(Rental rental: ownerRentals){
            ownerMov = ownerMov.concat("Cliente: "+ rental.getTenantUser().getName()+" "+ rental.getTenantUser().getSurname()+"\n"+
                            "Vehículo: "+ rental.getReservation().getPost().getVehicle().getDescription()+"\n"+
                            "Estado del alquiler: "+ rental.getState()+"\n\n"
            );
        }

        String tenantMov = "Momimientos de tus alquileres:\n";
        for(Rental rental: tenantRentals){
            tenantMov = tenantMov.concat("Dueño: "+ rental.getTenantUser().getName()+" "+ rental.getTenantUser().getSurname()+"\n"+
                    "Vehículo: "+ rental.getReservation().getPost().getVehicle().getDescription()+"\n"+
                    "Estado del alquiler: "+ rental.getState()+"\n\n"
            );
        }

        body = body.concat(ownerMov).concat(tenantMov);

        Mail.sendFromGMail(from.getEmail(), "[Carpnd] - Estado Mensual de cuenta", body);
    }

}
