package com.oktenweb.medbookback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TestNorm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Test test;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private int ageStart;
    private int ageEnd;
    private double normStart;
    private double normFinish;



}
