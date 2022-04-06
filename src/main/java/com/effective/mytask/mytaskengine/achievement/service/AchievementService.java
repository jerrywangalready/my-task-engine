package com.effective.mytask.mytaskengine.achievement.service;

import com.effective.mytask.mytaskengine.achievement.bo.AchievementBo;
import com.effective.mytask.mytaskengine.achievement.entity.AchievementEntity;

import java.util.List;

public interface AchievementService {

    List<AchievementBo> getAchievementTree(String parentId, String achievementGroup);

    List<AchievementBo> getAchievementTreeById(String id);

    AchievementEntity saveAchievement(AchievementEntity entity);

    boolean deleteAchievement(String id);
}
