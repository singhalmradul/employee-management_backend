package io.github.singhalmradul.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "announcement")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Announcement extends AbstractEntity {

    @Column(name = "title", nullable = false)
    @JsonProperty("title")
    private String title;

    @Column(name = "text", nullable = false)
    @JsonProperty("text")
    private String text;

    @Column(name = "date", columnDefinition = "DATE", nullable = false)
    @JsonProperty("date")
    private LocalDate date;

    @ManyToOne(optional = false, targetEntity = Department.class, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    @JsonProperty("department")

    @JsonIgnoreProperties(ignoreUnknown = true, allowSetters = true, value = { "announcements" })
    private Department department;

}
