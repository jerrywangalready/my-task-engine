package com.effective.mytask.mytaskengine.achievement.bo;

import com.effective.mytask.mytaskengine.achievement.entity.AchievementEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AchievementBo extends AchievementEntity {

    private List<AchievementBo> children = new ArrayList<>();
}
