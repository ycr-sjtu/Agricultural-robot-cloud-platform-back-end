package cn.edu.sjtu.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName("nj_test")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Robot {
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
