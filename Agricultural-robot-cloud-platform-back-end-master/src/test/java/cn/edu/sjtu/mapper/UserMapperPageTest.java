package cn.edu.sjtu.mapper;

import cn.edu.sjtu.entity.Robot;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperPageTest {

    @Autowired
    private RobotMapper robotMapper;


    @Test
    public void selectPage(){
        LambdaQueryWrapper<Robot> robotLambdaQueryWrapper = Wrappers.lambdaQuery();
        robotLambdaQueryWrapper.lt(Robot::getId , 200);  //查询id大于200的实体

        Page<Robot> robotPage = new Page<>(1 , 20);  //查询出来的结果，一页只能显示20条
        IPage<Robot> robotIPage = robotMapper.selectPage(robotPage , robotLambdaQueryWrapper);
        System.out.println("总页数： "+robotIPage.getPages());
        System.out.println("总记录数： "+robotIPage.getTotal());
        robotIPage.getRecords().forEach(System.out::println);
    }

}
//这段代码使用了Spring Boot框架中的一些组件，主要用于数据库操作，具体解释如下：
//1.	@RunWith(SpringRunner.class)：这是JUnit的注解，它用于在运行测试时启动Spring容器。
//2.	@SpringBootTest：这是Spring Boot的注解，用于启动整个Spring应用程序上下文，以便在测试中使用Spring Bean。
//3.	@Autowired：这是Spring的注解，用于自动装配一个Bean。在这个例子中，它被用来自动装配RobotMapper。
//4.	public void selectPage()：这是一个测试方法，使用了JUnit的@Test注解。它将测试分页查询方法selectPage()是否能够正确地返回预期的结果。
//5.	LambdaQueryWrapper<Robot> robotLambdaQueryWrapper = Wrappers.lambdaQuery()：这行代码创建了一个LambdaQueryWrapper对象，它是MyBatis Plus框架的一个查询构造器。这里使用了like方法查询Robot表中id以"140"为前缀的记录。
//6.	Page<Robot> robotPage = new Page<>(2 , 20)：这行代码创建了一个分页对象Page，用于控制查询结果的分页显示。这里查询第2页，每页20条记录。
//7.	IPage<Robot> robotIPage = robotMapper.selectPage(robotPage , robotLambdaQueryWrapper)：这行代码使用RobotMapper执行分页查询。selectPage方法接受两个参数，一个是分页对象，另一个是查询构造器。该方法将返回一个IPage对象，它包含了查询结果的分页信息。
//8.	robotIPage.getPages()：这行代码打印了查询结果的总页数。
//9.	robotIPage.getTotal()：这行代码打印了查询结果的总记录数。
//10.	robotIPage.getRecords().forEach(System.out::println)：这行代码打印了查询结果的每一条记录。getRecords()方法返回查询结果的记录列表，它可以使用Java 8的forEach方法来遍历并打印每条记录。


//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class UserMapperPageTest {
//
//    @Autowired
//    private RobotMapper robotMapper;
//
//    @Test
//    public void selectMapsPage(){
//        LambdaQueryWrapper<Robot> robotLambdaQueryWrapper = Wrappers.lambdaQuery();
//        robotLambdaQueryWrapper.select(Robot::getId).like(Robot::getId , 200);
//
//        Page<Map<String , Object>> mapPage = new Page<>(1 , 2 , true);
//        IPage<Map<String , Object>> mapIPage = robotMapper.selectMapsPage(mapPage , robotLambdaQueryWrapper);
//        System.out.println("总页数： "+mapIPage.getPages());
//        System.out.println("总记录数： "+mapIPage.getTotal());
//        mapIPage.getRecords().forEach(System.out::println);
//
//    }
//
//}
//这段代码是一个基于Spring框架的Java测试类，它使用了JUnit和Spring的测试注解来测试一个名为"selectMapsPage"的方法。
//@RunWith(SpringRunner.class)这个注解指定了测试运行器，用于运行该测试类。在这里，我们使用SpringRunner作为测试运行器。
//@SpringBootTest这个注解表示这是一个Spring Boot的集成测试类。它会启动整个Spring应用程序上下文，以便能够在测试中使用Spring组件和功能。
//在这个测试类中，我们注入了一个名为"userMapper"的Bean，它是一个由MyBatis框架自动生成的Mapper接口，用于访问数据库中的"User"表。
//在"selectMapsPage"方法中，我们使用了MyBatis-Plus提供的LambdaQueryWrapper来构造一个查询条件，使用了"like"操作符匹配"User"表中"username"字段的值，模糊查询以"k"开头的记录。
//我们还创建了一个Page对象来表示分页查询的信息，其中参数1表示当前页码，参数2表示每页显示的记录数，参数3表示是否查询总记录数。接着，我们调用了"userMapper"的selectMapsPage方法，将这个Page对象和查询条件作为参数传递进去。该方法将返回一个IPage对象，其中包含了满足查询条件的记录和分页信息。
//最后，我们打印出了查询结果的总页数和总记录数，以及每个记录的详细信息。


//Java中进行分页查询的主要原因是：
//减少网络传输量：当数据量较大时，一次性获取所有数据会导致网络传输量过大，可能会降低系统的性能。通过分页查询，可以将数据分成若干页，每次只获取指定页的数据，从而减少网络传输量。
//提高查询效率：当数据量较大时，一次性获取所有数据可能会导致查询效率较低，延长用户等待时间。通过分页查询，可以将查询结果分成若干页，每次只查询指定页的数据，从而提高查询效率。
//减少系统资源的消耗：一次性获取大量数据可能会占用大量系统资源，例如内存、CPU等。通过分页查询，可以将数据分成若干页，每次只加载指定页的数据，从而减少系统资源的消耗。
//提升用户体验：通过分页查询，可以将数据分成若干页，用户可以根据自己的需求进行翻页和查看，提升了用户的使用体验。
//综上所述，Java中进行分页查询可以提高系统性能、减少资源消耗、提升用户体验等多个方面的好处，因此在实际开发中非常常见。

