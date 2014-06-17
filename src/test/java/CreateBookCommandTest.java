
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.luckypants.command.CreateBookCommand;
import com.luckypants.model.Book;

public class CreateBookCommandTest{

	static CreateBookCommand create = new CreateBookCommand();;
	static Book book = null;

	@BeforeClass
	public static void prepareClass() {
		book = new Book();
	}

	@AfterClass
	public static void cleanup() {
		// cleanup activities, such as closing DB,
	}

	@Before
	public void prepare() {
		book.set_author_id("5391f80f7e53a5fe410f01e8");
	}

	@Test(timeout = 10000)
	public void testExecute() {
		try {
			create.execute(book);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test(expected = Exception.class, timeout = 10000)
	public void testNull() throws Exception {
		create.execute(null);
	}

}
