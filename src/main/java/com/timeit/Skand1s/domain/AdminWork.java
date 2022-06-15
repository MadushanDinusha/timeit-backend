package com.timeit.Skand1s.domain;

import javax.persistence.*;

@Entity
@Table(name = "admin_work")
public class AdminWork {

    @Id
    @Column(name = "admin_work_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long admin_work_id;
}
