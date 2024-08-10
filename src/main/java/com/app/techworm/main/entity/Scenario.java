
package com.app.techworm.main.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "scenarios", schema = "main")
public class Scenario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Değişiklik yapıldı
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "priority")
    private int priority;

    @Column(name = "insert_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime insertDate;

    @Column(name = "definition")
    private String definition;

    @Column(name = "is_active")
    @JsonProperty("is_active") // JSON ve entity adlarını eşleştirme
    private Boolean isActive;
}
