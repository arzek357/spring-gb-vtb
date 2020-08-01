package com.vtb.zolotarev.homeWork3.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "lots")
@NoArgsConstructor
@Getter
public class Lot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "actual_rate")
    private long actualRate;
    @ManyToOne
    @JoinColumn(name = "last_rate_user_id")
    private User user;
    @Version
    private Long version;

    //Сеттеры сделал вручную, а не ломбоком, для того чтобы иключить сеттер на поле version.
    // В ломбок по идее можно отключить ненужный сеттер при помощи аннотации @Transient, но
    //боюсь, что она также будет сбивать Hibernate при инициализации, поэтому решил поступить так
    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setActualRate(long actualRate) {
        this.actualRate = actualRate;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
