package com.fpf.mp.pojo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data                     //设置get和set放法
@NoArgsConstructor       //设置无参构造
@AllArgsConstructor      //设置有参构造
@TableName("tb_user")
public class User {

   //设置自增长的策略：
   @TableId(type = IdType.AUTO)
   private Long id;

   @TableField(select=false)       //查询时不返回该属性的值
   private String userName;
   private String password;
   private String name;
   private Integer age;

   @TableField("email")            //指定数据库表中的字段名
   private String mail;

   @TableField(exist = false)
   private String address;       //该字段在数据库当中不存在
}
