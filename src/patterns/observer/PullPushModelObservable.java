package patterns.observer;

public interface PullPushModelObservable {

    void attach(PullPushModelObserver observer);
    void detach(PullPushModelObserver observer);
    void detachAll();
    void notifyObservers();

}
