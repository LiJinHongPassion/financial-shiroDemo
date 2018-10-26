import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)		//±íÊ¾¼Ì³ÐÁËSpringJUnit4ClassRunnerÀà
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestMyBatis {
	private static Logger logger = Logger.getLogger(TestMyBatis.class);
//	private ApplicationContext ac = null;

//	@Before
//	public void before() {
//		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		userService = (IUserService) ac.getBean("userService");
//	}
 
	@Test
	public void test1() {
		// System.out.println(user.getUserName());
		// logger.info("Öµ£º"+user.getUserName());
	}


}
