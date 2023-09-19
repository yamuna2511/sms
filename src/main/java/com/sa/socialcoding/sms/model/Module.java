package com.sa.socialcoding.sms.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="MODULE")
public class Module
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MODULE_ID")
    private int moduleId;

    @Column(name = "MODULE_NAME")
    private String moduleName;

    @Column(name = "MODULE_DESCRIPTION")
    private String moduleDescription;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "MODULE_ID", referencedColumnName = "MODULE_ID")
    Set<Topic> topic = new HashSet<>();

}
