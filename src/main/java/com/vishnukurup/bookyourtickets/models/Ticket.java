package com.vishnukurup.bookyourtickets.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String seatNo;
    private Integer price;
    @ManyToOne
    @JoinColumn(name = "show_id",referencedColumnName = "id")
    private Show show;
}
