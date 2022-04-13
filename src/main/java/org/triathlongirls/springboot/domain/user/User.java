package org.triathlongirls.springboot.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.triathlongirls.springboot.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length =45, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String salt;

    private int writing_step;
}
