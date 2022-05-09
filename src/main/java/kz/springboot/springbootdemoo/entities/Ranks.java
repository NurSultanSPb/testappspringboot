package kz.springboot.springbootdemoo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ranks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ranks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "rank_name", length = 30)
    private String rankName;
}
