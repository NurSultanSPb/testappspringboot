package kz.springboot.springbootdemoo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "officers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Officers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "num")
    private int personalNumber;

    @Column(name = "surname", length = 15)
    private String surname;

    @Column(name = "name", length = 15)
    private String name;

    @Column(name = "middle_name", length = 15)
    private String middleName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "picture_file_name", length = 150)
    private String fileName;

    @Column(name = "date_of_sign")
    private LocalDate dateOfSign;

    @Column(name = "contract_period")
    private int contractPeriod;

    @ManyToOne(fetch = FetchType.EAGER)
    private Positions position;

    @ManyToOne(fetch = FetchType.EAGER)
    private Ranks rank;

    @ManyToOne(fetch = FetchType.EAGER)
    private Departments department;

}
