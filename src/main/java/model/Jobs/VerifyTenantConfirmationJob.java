package model.Jobs;


import model.Rental;
import model.states.rental.ConfirmedByTheOwnerST;
import model.states.rental.PendingReturnRentalST;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class VerifyTenantConfirmationJob implements Job{

    private Rental rental;

    public VerifyTenantConfirmationJob(Rental rental){
        this.rental = rental;
    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        if(this.rental.getState().getClass().equals(ConfirmedByTheOwnerST.class)) {
            this.rental.startRentalTime();
            this.rental.setState(new PendingReturnRentalST());
        }

    }
}
