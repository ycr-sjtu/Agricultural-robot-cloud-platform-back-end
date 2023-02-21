package cn.edu.sjtu.controller;

import cn.edu.sjtu.entity.Robot;
import cn.edu.sjtu.service.RobotService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/robot")
public class RobotController {

    private final RobotService robotService;

    public RobotController(RobotService robotService) {
        this.robotService = robotService;
    }

    @GetMapping("/list")
    public List<Robot> getList() {
        return robotService.list();
    }

    @PostMapping
    public String save(@RequestBody Robot robot) {
        if (robot.getLng() > -180 && robot.getLng() < 180 && robot.getLat() > -90 && robot.getLat() < 90) {
            robot.setCategory("未知类型");
            robot.setRobotNumber("未知型号");
            robot.setTime(new Date());
            robotService.save(robot);
            return "新增成功";
        }

        return "新增失败";
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") Long id) {
        if (robotService.removeById(id)) {
            return "删除成功";
        }

        return "删除失败";
    }
}
