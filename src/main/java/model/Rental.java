package model;


import model.Jobs.VerifyTenantConfirmationJob;
import model.exceptions.CanceledRentalException;
import model.interfaces.IRentalState;
import model.states.rental.PendingRentalST;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class Rental {

    private Reservation reservation;
    private IRentalState state;
    private LocalDateTime beginRentalTime = null;
    private LocalDateTime timeAfterTheOwnerConfirmation = null;
    private LocalDateTime timeAfterTheTenantConfirmation = null;
    private LocalDateTime endRentalTime = null;

    public Rental(Reservation reservation){

        this.reservation = reservation;
        this.state = new PendingRentalST();

    }

    public IRentalState getState(){
        return this.state;
    }

    public void ownerConfirmation(){
        this.state.ownerUserConfirmated(this);

        try {
            SchedulerFactory sf = new StdSchedulerFactory();
            Scheduler scheduler = sf.getScheduler();
            scheduler.start();

            JobDetail job = newJob(VerifyTenantConfirmationJob.class)
                    .withIdentity("job", Scheduler.DEFAULT_GROUP)
                    .build();

            Trigger trigger = newTrigger()
                    .withIdentity("trigger", Scheduler.DEFAULT_GROUP)
                    .startNow()
                    .withSchedule(simpleSchedule())
                    .build();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            scheduler.scheduleJob(job, trigger);

        }catch (SchedulerException exception){
            exception.printStackTrace();
        }

    }

    public void tenantConfirmation(){
        this.state.tenantUserConfirmated(this);
    }

    public void setState(IRentalState newState){
        this.state=newState;
    }

    public void startTimeAfterTheOwnerConfirmation(){
        this.timeAfterTheOwnerConfirmation= LocalDateTime.now();
    }

    public void startTimeAfterTheTenantConfirmation(){
        this.timeAfterTheTenantConfirmation= LocalDateTime.now();
    }

    public void startRentalTime(){
        this.beginRentalTime = LocalDateTime.now();
    }

    public long getRentalTime(){
        return this.beginRentalTime.until(LocalDateTime.now(), ChronoUnit.DAYS);
    }

    public User getTenantUser(){
        return this.reservation.getTenantUser();
    }

    public User getOwnerUser(){
        return this.reservation.getOwnerUser();
    }

    //pensar esto de nuevo estos checks.... no me cierra
    public void checkTheTimeAfterTheTenantConfirmation(){

        long tenantConfirmation = this.timeAfterTheTenantConfirmation.
                until(LocalDateTime.now(), ChronoUnit.MINUTES);

        if(tenantConfirmation>30) {
            throw new CanceledRentalException();
        }
    }

    public double rentCost(LocalDateTime endRentalTime) {
        this.endRentalTime = endRentalTime;
        long days= this.beginRentalTime.until(endRentalTime, ChronoUnit.DAYS);
        return this.reservation.getPost().getCostPerDay()*days;
    }

    public void tenantUserConfirmatedReturn(int score, String comment) {
        this.getState().tenantUserConfirmated(this, score, comment);
    }

    public void ownerUserConfirmatedReturn(int score, String comment) {
        this.getState().ownerUserConfirmated(this, score, comment);
    }
}
