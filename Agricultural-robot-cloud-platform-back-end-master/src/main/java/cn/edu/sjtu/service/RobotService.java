package cn.edu.sjtu.service;

import cn.edu.sjtu.entity.Robot;
import cn.edu.sjtu.vo.RobotVo;
import cn.edu.sjtu.vo.ResponseVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


//import com.baomidou.mybatisplus.extension.service.IService;
//public interface RobotService extends IService<Robot> {
//}


public interface RobotService extends IService<Robot> {
    ResponseVo<IPage<Robot>> products(Integer pageNum, Integer pageSize , String category_2);
}
