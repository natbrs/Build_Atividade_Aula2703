import java.util.ArrayList;
import java.util.List;

interface Subject {
    public void attach(Observer observer);
    public void detach(Observer observer);
    public void notifyObservers(int newState);
}

interface Observer {
    public void update(int newState);
}

class ConcreteSubject implements Subject {
    private final List<Observer> observers = new ArrayList<>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyObservers(state);
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(int newState) {
        for (Observer observer : observers) {
            observer.update(newState);
        }
    }
}

class ConcreteObserver implements Observer {
    private int observerState;
    private ConcreteSubject subject;

    public ConcreteObserver(ConcreteSubject subject) {
        this.subject = subject;
        subject.attach(this);
    }

    public void update(int newState) {
        observerState = newState;
        System.out.println("Observer state updated to: " + observerState);
    }
}

public class Main {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();

        ConcreteObserver observer = new ConcreteObserver(subject);

        subject.setState(1);
        subject.setState(2);
        subject.detach(observer);

        subject.setState(3);
    }
}
