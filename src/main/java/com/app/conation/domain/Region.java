package com.app.conation.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "region")
public class Region extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "regionName")
    private String regionName;

    @Column(name = "accumulatedDonationScore")
    private Long accumulatedDonationScore;

    @OneToMany(mappedBy = "regionId", cascade = CascadeType.ALL)
    private List<User> users;

    @OneToOne(mappedBy = "regionId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private TodayDonationStatus donationStatusId;

    public void addUser(User user) {
        users.add(user);
        user.setRegionId(this);
    }

}
