package com.oktenweb.medbookback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class VisitToDoctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String date;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Doctor doctor;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Patient patient;
    private String conclusion;

    @Override
    public String toString() {
        return "VisitToDoctor{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", conclusion='" + conclusion + '\'' +
                '}';
    }
}
