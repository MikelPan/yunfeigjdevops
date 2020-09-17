package site.plyx.yunfeigjdevops;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import site.plyx.yunfeigjdevops.model.User;
import site.plyx.yunfeigjdevops.service.UserService;

import javax.annotation.Resource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

@RunWith(SpringRunner.class)
@SpringBootTest
class YunfeigjDevopsApplicationTests {

	@Test
	void contextLoads() {
	}

	@Resource
	private UserService userService;

	/**
	 * 测试增删查改
	 */

	@Test
	public void test() {
		/**
		 * 插入数据
		 */
		User user = new User();
		user.setName("admin");
		user.setGender("男");
		userService.insertUser(user);

		/**
		 * 查询数据
		 */
		User user1 = userService.findById(user.getId());
		assertThat(user1.getName(), is("admin"));
		assertThat(user1.getGender(), is("男"));

		/**
		 * 更新数据
		 */
		user1.setPhone(1234567890);
		userService.updateUser(user1);
		User user2 = userService.findById(user.getId());
		assertThat(user2.getPhone(), is(1234567890));

		/**
		 * 删除数据
		 */
		userService.deleteUser(user.getId());
		User user3 = userService.findById(user.getId());
		assertThat(user3, nullValue());
	}

}
