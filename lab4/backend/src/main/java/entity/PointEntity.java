package entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "results")
@NamedQueries({
        @NamedQuery(name = "PointEntity.findByUserId", query = "SELECT r FROM PointEntity r WHERE r.user.id = :userId"),
        @NamedQuery(name = "PointEntity.deleteByUserId", query = "DELETE FROM PointEntity r WHERE r.user.id = :userId")
})
public class PointEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    private double x;
    private double y;
    private double r;
    private String hit;

    public void setHit(boolean hit) {
        this.hit = hit ? "yes" : "no";
    }
}
