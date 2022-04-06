package com.effective.mytask.mytaskengine.achievement.mapper;

import com.effective.mytask.mytaskengine.achievement.bo.AchievementBo;
import com.effective.mytask.mytaskengine.achievement.entity.AchievementEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AchievementMapper {

    List<AchievementBo> getAchievements(AchievementEntity entity);

    List<AchievementBo> getChildrenAchievements(AchievementEntity entity);

    Integer addAchievement(AchievementEntity entity);

    Integer updateAchievement(AchievementEntity entity);

    Integer deleteAchievement(String id);
}
