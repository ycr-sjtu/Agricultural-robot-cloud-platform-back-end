package cn.edu.sjtu.controller;
import cn.edu.sjtu.entity.Robot;
import cn.edu.sjtu.service.RobotService;
import cn.edu.sjtu.vo.ResponseVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("/robot")
public class RobotController {
    @Resource
    private final RobotService robotService;


    public RobotController(RobotService robotService) {
        this.robotService = robotService;
    }


    @CrossOrigin
    @RequestMapping("/list")
    public ResponseVo<IPage<Robot>> getList(@RequestParam(defaultValue = "1") Integer pageNum ,
                                            @RequestParam(defaultValue = "20") Integer pageSize ,
                                            @RequestParam String category_2){
        return robotService.products(pageNum , pageSize , category_2);
    }

    @CrossOrigin
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

    @CrossOrigin
    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") Long id) {
        if (robotService.removeById(id)) {
            return "删除成功";
        }

        return "删除失败";
    }


}


