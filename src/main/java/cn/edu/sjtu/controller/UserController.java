package cn.edu.sjtu.controller;
import cn.edu.sjtu.config.Result;
import cn.edu.sjtu.entity.User;
import cn.edu.sjtu.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class UserController {

    @Resource
    UserMapper userMapper;

    @CrossOrigin
    @PostMapping
    public Result login(@RequestBody User user){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername()).eq("password", user.getPassword());
        User res = userMapper.selectOne(queryWrapper);
        if (res == null) {
            return Result.error("-1", "用户名或密码错误");
        }
        return Result.success();
    }
}
