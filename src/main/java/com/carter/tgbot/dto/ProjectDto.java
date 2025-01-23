package com.carter.tgbot.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "project_dto")
public class ProjectDto {
    @Id
    private int id;
    private String status;
    private String dateCreate;
    private String dateActive;
    private String dateExpire;
    private String wantUserGetProfileUrl;
    private int possiblePriceLimit;
    private int priceLimit;
    private String parentCategoryId;
    @Column(length = 5000)
    private String description;
    private String kworkCount;
    private String fname;
    private String url;
    private String name;
    private int viewsDirty;
    private String timeLeft;
    private String categoryId;

}
