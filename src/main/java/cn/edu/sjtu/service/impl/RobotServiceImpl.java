package cn.edu.sjtu.service.impl;
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

