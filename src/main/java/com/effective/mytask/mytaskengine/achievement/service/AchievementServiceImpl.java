package com.effective.mytask.mytaskengine.achievement.service;

import com.effective.mytask.mytaskengine.achievement.bo.AchievementBo;
import com.effective.mytask.mytaskengine.achievement.entity.AchievementEntity;
import com.effective.mytask.mytaskengine.achievement.mapper.AchievementMapper;
import com.effective.mytask.mytaskengine.util.IdFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class AchievementServiceImpl implements AchievementService {

    @Resource
    private AchievementMapper achievementMapper;

    @Override
    public List<AchievementBo> getAchievementTree(String parentId, String achievementGroup) {
        AchievementEntity entity = new AchievementEntity();
        entity.setAchievementGroup(achievementGroup);
        List<AchievementBo> achievements = achievementMapper.getAchievements(entity);

        return treeCombiner(achievements);

    }

    @Override
    public List<AchievementBo> getAchievementTreeById(String id) {
        AchievementEntity entity = new AchievementEntity();
        entity.setId(id);
        List<AchievementBo> achievement = achievementMapper.getAchievements(entity);

        List<AchievementBo> achievements = achievementMapper.getChildrenAchievements(achievement.get(0));

        achievements.add(achievement.get(0));

        return treeCombiner(achievements);
    }

    private List<AchievementBo> treeCombiner(List<AchievementBo> achievements) {
        Map<String, List<AchievementBo>> tempMap = new HashMap<>();

        achievements.forEach(achievementBo -> {

            String id = achievementBo.getId();
            if (tempMap.containsKey(id)) {
                // 将临时map中的自己的子项目与自己捆绑
                achievementBo.getChildren().addAll(tempMap.get(id));
                // 删除捆绑过的子项目
                tempMap.remove(id);
            }

            tempMap.merge(
                    achievementBo.getParentId(),
                    new ArrayList<>(Collections.singletonList(achievementBo)),
                    (oldValue, newValue) -> {
                        oldValue.add(newValue.get(0));
                        return oldValue;
                    }
            );

        });

        return tempMap.get(achievements.get(achievements.size()-1).getParentId());
    }


    @Override
    public AchievementEntity saveAchievement(AchievementEntity entity) {

        if (entity.getId() == null) {
            entity.setId(IdFactory.generateId());
            achievementMapper.addAchievement(entity);
        } else {
            achievementMapper.updateAchievement(entity);
        }
        return entity;
    }

    @Override
    public boolean deleteAchievement(String id) {
        achievementMapper.deleteAchievement(id);
        return true;
    }

    public static void main(String[] args) {
        AchievementServiceImpl achievementService = new AchievementServiceImpl();
        int[] nums = {-1,0,3,5,9,12};
        int result = achievementService.search(nums, 5);
        System.out.println(result);
    }

    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            int num = nums[mid];
            if (num == target) {
                return mid;
            } else if (num > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }


}

