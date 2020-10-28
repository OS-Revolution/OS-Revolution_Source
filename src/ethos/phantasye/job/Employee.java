package ethos.phantasye.job;

public interface Employee extends  JobObserver {

    int POINT_VALUE = 10;
    int BASE_PAY = 10000;
    int BONUS_PAY = 50000;


    void assignJob(Job job);

    void updateJob(int itemId);

    void completeJob();

    void quit();

}
