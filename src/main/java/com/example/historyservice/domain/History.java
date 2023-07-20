package com.example.historyservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name="history")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class History {
    //historyId, userId, postId, viewDate
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Integer historyId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "post_id")
    private Integer postId;

    @Column(name = "view_date")
    private Date viewDate;
}
