package com.timeit.Skand1s.domain;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "cases",uniqueConstraints = {@UniqueConstraint(columnNames={ "oldestDate","user_id"})})
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int fPriorityNum;
    private int fPriorityOldNum;
    private int sPriorityNum;
    private int sPriorityOldNum;

    private Timestamp oldestDate;
    private int weekNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    //@JsonIgnore
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getfPriorityNum() {
        return fPriorityNum;
    }

    public void setfPriorityNum(int fPriorityNum) {
        this.fPriorityNum = fPriorityNum;
    }

    public int getfPriorityOldNum() {
        return fPriorityOldNum;
    }

    public void setfPriorityOldNum(int fPriorityOldNum) {
        this.fPriorityOldNum = fPriorityOldNum;
    }

    public int getsPriorityNum() {
        return sPriorityNum;
    }

    public void setsPriorityNum(int sPriorityNum) {
        this.sPriorityNum = sPriorityNum;
    }

    public int getsPriorityOldNum() {
        return sPriorityOldNum;
    }

    public void setsPriorityOldNum(int sPriorityOldNum) {
        this.sPriorityOldNum = sPriorityOldNum;
    }

    public Timestamp getOldestDate() {
        return oldestDate;
    }

    public void setOldestDate(Timestamp oldestDate) {
        this.oldestDate = oldestDate;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Case{" +
                "id=" + id +
                ", fPriorityNum=" + fPriorityNum +
                ", fPriorityOldNum=" + fPriorityOldNum +
                ", sPriorityNum=" + sPriorityNum +
                ", sPriorityOldNum=" + sPriorityOldNum +
                ", oldestDate=" + oldestDate +
                ", weekNumber=" + weekNumber +
                ", user=" + user +
                '}';
    }
}
