package model.jobs;

import model.Rental;
import model.states.rental.ConfirmedByTheOwnerST;
import model.states.rental.PendingReturnRentalST;
import org.quartz.*;

public class VerifyTenantConfirmationJob implements Job{

    public static final String RENTAL = "rental";

    public void execute(JobExecutionContext context) throws JobExecutionException {

        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        Rental rental = (Rental) dataMap.get(RENTAL);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(rental.getState().getClass().equals(ConfirmedByTheOwnerST.class)) {
            rental.startRentalTime();
            rental.setState(new PendingReturnRentalST());
        }

        context.put(VerifyTenantConfirmationJob.RENTAL, rental);
    }
}
