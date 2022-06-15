package com.timeit.Skand1s.domain;

import javax.persistence.*;

@Entity
@Table(name = "work")
public class Work {

    @Id
    @Column(name = "work_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long work_id;

    @OneToOne(targetEntity=User.class)
    private User user;

    public enum Shift{
        Morning,
        Afternoon,
        Evening
    }
    private Shift shift;

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }


    public long getWork_id() {
        return work_id;
    }

    public void setWork_id(long work_id) {
        this.work_id = work_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Work{" +
                "work_id=" + work_id +
                ", user=" + user +
                ", shift=" + shift +
                '}';
    }
}
