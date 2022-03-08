package com.assetmgmt.entity;

import com.assetmgmt.entity.MasterLessor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table()
@Data
public class BankDetails  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 30)
    private String bankName;
    @Column(length = 30)
    private String accountNumber;
    @Column(length = 30)
    private String accountName;
    @Column(length = 30)
    private String type;
    @Column(length = 30)
    private String branchName;
    @Column(length = 30)
    private String ifscCode;
}