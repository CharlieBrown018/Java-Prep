package com.interview.java;

import java.util.ArrayList;
import java.util.List;

// Observer
interface Subscriber {
    void update(String news);
}

// Subject (Observable)
class NewsPublisher {
    private List<Subscriber> subscribers = new ArrayList<>();
    private String latestNews;

    public void subscribe(Subscriber sub) {
        subscribers.add(sub);
    }

    public void unsubscribe(Subscriber sub) {
        subscribers.remove(sub);
    }

    public void notifySubscribers() {
        for (Subscriber sub : subscribers) {
            sub.update(latestNews);
        }
    }

    public void publishNews(String news) {
        this.latestNews = news;
        notifySubscribers();
    }
}

// Concrete Observer
class EmailSubscriber implements Subscriber {
    private String name;

    public EmailSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String news) {
        System.out.println(name + " received news update: " + news);
    }
}

public class Q23_ObserverPattern {
    public static void main(String[] args) {
        NewsPublisher publisher = new NewsPublisher();
        Subscriber sub1 = new EmailSubscriber("Alice");
        Subscriber sub2 = new EmailSubscriber("Bob");

        publisher.subscribe(sub1);
        publisher.subscribe(sub2);

        publisher.publishNews("Java 21 Released!");
    }
}
