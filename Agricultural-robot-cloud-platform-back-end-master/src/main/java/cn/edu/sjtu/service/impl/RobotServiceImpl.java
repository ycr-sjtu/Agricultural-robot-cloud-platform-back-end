package cn.edu.sjtu.service.impl;

//import cn.edu.sjtu.entity.Robot;
//import cn.edu.sjtu.mapper.RobotMapper;
//import cn.edu.sjtu.service.RobotService;
//import cn.edu.sjtu.vo.RobotVo;
//import cn.edu.sjtu.vo.ResponseVo;
//import com.baomidou.mybatisplus.core.conditions.Wrapper;
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.core.toolkit.Wrappers;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.Map;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
//@Service
//public class RobotServiceImpl implements RobotService {
//
////    @Autowired
////    private RobotMapper robotMapper;
//
//
//    @Override
//    public ResponseVo<IPage<RobotVo>> products(Integer pageNum, Integer pageSize , String category_2) {
//
//        LambdaQueryWrapper<Robot> robotQuery = Wrappers.lambdaQuery();
//        robotQuery.eq(Robot::getCategory , category_2);
//        Page<Robot> page = new Page(pageNum , pageSize);
//        IPage<Robot> robotIPage = selectPage(page , robotQuery);
//
//        IPage<RobotVo> robotVoIPage = new Page<>();
//        BeanUtils.copyProperties(robotIPage , robotVoIPage);
//
//        List<RobotVo> robotVoList = robotIPage.getRecords().stream().map(
//                e->robot2RobotVo(e)
//        ).collect(Collectors.toList());
//        robotVoIPage.setRecords(robotVoList);
//
//        return ResponseVo.success(robotVoIPage);
//    }
//
//    private RobotVo robot2RobotVo(Robot robot){
//        RobotVo robotVo = new RobotVo();
//        BeanUtils.copyProperties(robot , robotVo);
//        return robotVo;
//    }
//
////    @Override
////    public boolean saveBatch(Collection<Robot> entityList, int batchSize) {
////        return false;
////    }
////
////    @Override
////    public boolean saveOrUpdateBatch(Collection<Robot> entityList, int batchSize) {
////        return false;
////    }
////
////    @Override
////    public boolean updateBatchById(Collection<Robot> entityList, int batchSize) {
////        return false;
////    }
////
////    @Override
////    public boolean saveOrUpdate(Robot entity) {
////        return false;
////    }
////
////    @Override
////    public Robot getOne(Wrapper<Robot> queryWrapper, boolean throwEx) {
////        return null;
////    }
////
////    @Override
////    public Map<String, Object> getMap(Wrapper<Robot> queryWrapper) {
////        return null;
////    }
////
////    @Override
////    public <V> V getObj(Wrapper<Robot> queryWrapper, Function<? super Object, V> mapper) {
////        return null;
////    }
////
////    @Override
////    public BaseMapper<Robot> getBaseMapper() {
////        return null;
////    }
////
////    @Override
////    public Class<Robot> getEntityClass() {
////        return null;
////    }
//}

import cn.edu.sjtu.entity.Robot;
import cn.edu.sjtu.mapper.RobotMapper;
import cn.edu.sjtu.service.RobotService;
import cn.edu.sjtu.vo.ResponseVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RobotServiceImpl extends ServiceImpl<RobotMapper, Robot> implements RobotService {
    @Override
    public ResponseVo<IPage<Robot>> products(Integer pageNum, Integer pageSize, String category_2) {
        Page<Robot> page = new Page<>(pageNum, pageSize);
        page(page);
        return ResponseVo.success(page);
    }

}

