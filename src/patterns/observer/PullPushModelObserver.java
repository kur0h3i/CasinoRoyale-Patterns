package patterns.observer;

public interface PullPushModelObserver {

    void update(PullPushModelObservable pullPushModelObservable, Object object);
    // void update(PullPushModelObservable pullPushModelObservable);

}
