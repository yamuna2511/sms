package com.sa.socialcoding.sms.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="TOPIC")
public class Topic
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TOPIC_ID")
    private int topicId;

    @Column(name = "TOPIC_NAME")
    private String topicName;

    @Column(name = "SEQ_NO")
    private int seqNo;

    @Column(name = "TOPIC_TYPE")
    private String topicType;

    @Column(name = "TOPIC_DESCRIPTION")
    private String topicDescription;
}
