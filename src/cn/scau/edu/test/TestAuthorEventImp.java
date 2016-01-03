package cn.scau.edu.test;

import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.Test;

import cn.scau.edu.dao.RegisterEvent;
import cn.scau.edu.pojo.Author;
import cn.scau.edu.util.ClassFactory;

public class TestAuthorEventImp {
	private static RegisterEvent re = ClassFactory.getInstance().getRegisterEvent();
	
	@Test
	public void testAddRegister() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testShow() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckAuthor() throws SQLException {
		Author author = re.checkAuthor("xiaoming", "xiaoming1");
		System.out.println(author);
	}

}
