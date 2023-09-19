package com.sa.socialcoding.sms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="file")
public class File {
    @Id
    @Column(name = "FILE_ID")
    private int fileId;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "FILE_TYPE")
    private String fileType;

    @Lob
    @Column(name = "DATA")
    private byte[] data;
}
