package cn.edu.sjtu.service.impl;

import cn.edu.sjtu.mapper.RobotMapper;
import cn.edu.sjtu.entity.Robot;
import cn.edu.sjtu.service.RobotService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RobotServiceImpl extends ServiceImpl<RobotMapper, Robot> implements RobotService {
}
