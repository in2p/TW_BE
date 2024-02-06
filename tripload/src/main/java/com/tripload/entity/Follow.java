package com.tripload.entity;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name="subscribe_uk",
                        columnNames = {"toMemberId", "fromMemberId"}
                )
        }
)
public class Follow {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;


    private Long toMemberId;

    @JoinColumn(name = "fromMemberId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member fromMember;

    public Follow(Long toMemberId, Member fromMember) {
        this.toMemberId = toMemberId;
        this.fromMember = fromMember;
    }
}
