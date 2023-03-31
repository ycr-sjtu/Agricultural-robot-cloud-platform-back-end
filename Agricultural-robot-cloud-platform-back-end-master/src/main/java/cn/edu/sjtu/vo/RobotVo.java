package cn.edu.sjtu.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class RobotVo {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String category;
    private String robotNumber;
    // 经度
    private Double lng;
    // 纬度
    private Double lat;
    private String url;
    private Double vel;
    private Double freq1;

    private Double freq2;
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm", timezone="Asia/Shanghai")
    private Date time;
}

