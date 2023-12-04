package io.github.singhalmradul.empoyeemanagement.entities;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "meeting")
@Getter
@Setter
public class Meeting {

    public enum Status {
        PENDING, COMPLETED, ONGOING
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "date", columnDefinition = "DATE", nullable = false)
    private LocalDate date;

    @Column(name = "start_time", columnDefinition = "TIME", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", columnDefinition = "TIME", nullable = false)
    private LocalTime endTime;

    @Column(name = "location", nullable = false)
    private String location;

    @ManyToMany(targetEntity = Employee.class, cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinTable(name = "meeting_attendees", joinColumns = @JoinColumn(name = "meeting_id"), inverseJoinColumns = @JoinColumn(name = "employee_id"))
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

    public boolean isPending() {
        return this.getStatus() == Status.PENDING;
    }

    public boolean isOngoing() {
        return this.getStatus() == Status.ONGOING;
    }

    public boolean isCompleted() {
        return this.getStatus() == Status.COMPLETED;
    }

    public Duration getDuration() {
        return Duration.between(this.startTime, this.endTime);
    }
}
