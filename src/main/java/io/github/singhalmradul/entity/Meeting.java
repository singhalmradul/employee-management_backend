package io.github.singhalmradul.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "meeting")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Meeting extends AbstractEntity {

    public enum Status {
        PENDING, COMPLETED, ONGOING
    }

    @Column(name = "title", nullable = false)
    @JsonProperty("title")
    private String title;

    @Column(name = "description", nullable = false)
    @JsonProperty("description")
    private String description;

    @Column(name = "date", columnDefinition = "DATE", nullable = false)
    @JsonProperty("date")
    private LocalDate date;

    @Column(name = "start_time", columnDefinition = "TIME", nullable = false)
    @JsonProperty("start_time")
    private LocalTime startTime;

    @Column(name = "end_time", columnDefinition = "TIME", nullable = false)
    @JsonProperty("end_time")
    private LocalTime endTime;

    @Column(name = "location", nullable = false)
    @JsonProperty("location")
    private String location;

    @Column(name = "status", nullable = false)
    @JsonProperty("status")
    private String status;

    @ManyToMany(targetEntity = Employee.class, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinTable(name = "meeting_attendees", joinColumns = @JoinColumn(name = "meeting_id"), inverseJoinColumns = @JoinColumn(name = "employee_id"))
    @JsonProperty("attendees")
    @JsonIgnoreProperties(ignoreUnknown = true, allowSetters = true, value = { "meetings" })
    private List<Employee> attendees;

    @JsonProperty("status")
    public Status getStatus() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime meetingStart = LocalDateTime.of(this.date, this.startTime);
        LocalDateTime meetingEnd = LocalDateTime.of(this.date, this.endTime);
        if (now.isBefore(meetingStart)) {
            return Status.PENDING;
        } else if (now.isAfter(meetingStart) && now.isBefore(meetingEnd)) {
            return Status.ONGOING;
        } else {
            return Status.COMPLETED;
        }
    }

}
