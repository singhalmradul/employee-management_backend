package io.github.singhalmradul.entity;

import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "shift")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Shift extends AbstractEntity {

    @Column(name = "start_time", columnDefinition = "TIME", nullable = false)
    @JsonProperty("start_time")
    private LocalTime startTime;

    @Column(name = "end_time", columnDefinition = "TIME", nullable = false)
    @JsonProperty("end_time")
    private LocalTime endTime;

    @ManyToMany(targetEntity = Entity.class, mappedBy = "shifts", cascade = { CascadeType.PERSIST,
            CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JsonProperty("employees")
    @JsonIgnoreProperties(ignoreUnknown = true, allowSetters = true, value = { "shifts" })
    private transient List<Employee> employees;

}
