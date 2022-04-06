package com.effective.mytask.mytaskengine.achievement.entity;

import lombok.Data;

@Data
public class AchievementEntity {

    private String id;

    private String achievementName;

    private String parentId;

    private Integer levelNum;

    private String achievementGroup;
}
