package com.effective.mytask.mytaskengine.achievement.controller;


import com.effective.mytask.mytaskengine.achievement.bo.AchievementBo;
import com.effective.mytask.mytaskengine.achievement.entity.AchievementEntity;
import com.effective.mytask.mytaskengine.achievement.service.AchievementService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/achievement")
public class AchievementController {

    @Resource
    private AchievementService achievementService;

    @GetMapping("/getAchievementTree")
    public List<AchievementBo> getAchievementTree(AchievementEntity entity) {
        return achievementService.getAchievementTree(entity.getParentId(), entity.getAchievementGroup());
    }

    @GetMapping("/getAchievementTree1")
    public AchievementBo getAchievementTree1(AchievementEntity entity) {
        return achievementService.getAchievementTree(entity.getParentId(), entity.getAchievementGroup()).get(0);
//        return null;
    }

    @GetMapping("/getAchievementTreeById")
    public List<AchievementBo> getAchievementTreeById(@RequestParam("id") String id) {
        return achievementService.getAchievementTreeById(id);
    }

    @GetMapping("/getAchievementTreeById1")
    public AchievementBo getAchievementTreeById1(@RequestParam("id") String id) {
        return achievementService.getAchievementTreeById(id).get(0);
    }

    @PostMapping("/saveAchievement")
    public AchievementEntity saveAchievement(@RequestBody AchievementEntity entity) {
        return achievementService.saveAchievement(entity);
    }

    @DeleteMapping("/deleteAchievement")
    public boolean deleteAchievement(@RequestBody AchievementEntity entity) {
        return achievementService.deleteAchievement(entity.getId());
    }
}
