package com.example.todoapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity // Oznacza tabelę w bazie która odpowiada takiej klasie
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
public class Task  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private int id;
    @Column(name = "description")
    @NotBlank(message = "Opis nie może być pusty")
    private String description;
    // @Transient - oznacza że tego pola nie chcemy zapisywać do bazy danych, ale w request możemy tam wrzucic jakas wartosc
    @Column(columnDefinition = "BOOLEAN")
    private boolean done;
    @Column(name = "deadline")
    private LocalDateTime deadline;
    @Embedded // Osadzamy obiekt ktory jest @Embeddable
//    @AttributeOverride() Pojedyncze nadpisanie nazwy pola
//    @AttributeOverrides({
//            @AttributeOverride(column = @Column(name = "updatedOn"), name="updatedOn")
//    })
    private Audit audit = new Audit();
    @ManyToOne // Wiele tasków do jednej grupy, Cascade - task zarządza grupą
    @JoinColumn(name = "task_group_id")
    @Getter(AccessLevel.PACKAGE)
    @Setter(AccessLevel.NONE)
    private TaskGroup group;

    public Task(String description, LocalDateTime deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    public void updateFrom(final Task source) {
        description = source.description;
        done = source.done;
        deadline = source.deadline;
        group = source.group;
    }
}
