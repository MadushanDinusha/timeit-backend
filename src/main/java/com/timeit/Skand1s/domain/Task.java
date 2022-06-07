package com.timeit.Skand1s.domain;

import javax.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public enum Type{
        Phone,
        Meeting,
        BBØ,
        HO,
        Mails,
        Fri,
        Syg,
        Andet
    }
    private Type type;
    private Timestamp fromDate;
    private Timestamp toDate;
    private boolean isActive;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Timestamp getFromDate() {
        return fromDate;
    }

    public void setFromDate(Timestamp fromDate) {
        this.fromDate = fromDate;
    }

    public Timestamp getToDate() {
        return toDate;
    }

    public void setToDate(Timestamp toDate) {
        this.toDate = toDate;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", type=" + type +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", isActive=" + isActive +
                ", user=" + user +
                '}';
    }
}
