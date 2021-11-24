package ar.com.frigeriofranco.practic.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Table(name = "clients")
@SQLDelete(sql = "UPDATE clients SET deleted = true WHERE id=?")
@Where(clause = " deleted = false ")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Client extends Person{


    private String sold;


   /**/

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "clients_bills",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "bills_id"))
    @JsonManagedReference
    private List<Bill> bills;


    @PrePersist
    public void prePersist() {
        bills = new ArrayList<>();
    }
}
