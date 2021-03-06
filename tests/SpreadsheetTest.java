import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class SpreadsheetTest {
	Spreadsheet sheet;
	
	@Before
	public void initilize() {
		sheet = new Spreadsheet();
	}

	@Test
	public void testAssignment_A1_Negative_1() {
		sheet.set("A1", "-1");
		String result = sheet.evaluate("A1");
		assertEquals("-1", result);
	}

	@Test
	public void testAssignment_A1_Postive_1() {
		sheet.set("A1", "1");
		String result = sheet.evaluate("A1");
		assertEquals("1", result);
	}

	@Test
	public void testAssignmentInvalidInteger_Excess_DecimalPoint_Error() {
		sheet.set("A1", "1.");
		String result = sheet.evaluate("A1");
		assertEquals("#Error", result);
	}

	@Test
	public void testAssignmentInvalidInteger_Excess_A_Error() {
		sheet.set("A1", "5A");
		String result = sheet.evaluate("A1");
		assertEquals("#Error", result);
	}

	@Test
	public void testAssignment_A1_ValidString() {
		sheet.set("A1", "'a string'");
		String result = sheet.evaluate("A1");
		assertEquals("a string", result);
	}

	@Test
	public void testAssignment_A1_String_Missing_SingleQuote_Begin_Error() {
		sheet.set("A1", "a string'");
		String result = sheet.evaluate("A1");
		assertEquals("#Error", result);
	}

	@Test
	public void testAssignment_A1_String_Missing_SingleQuote_End_Error() {
		sheet.set("A1", "'a string");
		String result = sheet.evaluate("A1");
		assertEquals("#Error", result);
	}

	@Test
	public void testAssignment_A1_Assign_String() {
		sheet.set("A1", "='a string'");
		String result = sheet.evaluate("A1");
		assertEquals("a string", result);
	}

	@Test
	public void testAssignment_A1_Assign_String_Missing_SingleQuote_Begin_Error() {
		sheet.set("A1", "=a string'");
		String result = sheet.evaluate("A1");
		assertEquals("#Error", result);
	}

	@Test
	public void testAssignment_A1_Assign_String_Missing_SingleQuote_End_Error() {
		sheet.set("A1", "='a string");
		String result = sheet.evaluate("A1");
		assertEquals("#Error", result);
	}

	@Test
	public void testAssignment_A1_Assign_Integer_Positive() {
		sheet.set("A1", "=1");
		String result = sheet.evaluate("A1");
		assertEquals("1", result);
	}

	@Test
	public void testAssignment_A1_Assign_Integer_Negative() {
		sheet.set("A1", "=-1");
		String result = sheet.evaluate("A1");
		assertEquals("-1", result);
	}
	
	@Test
	public void testAssignment_A2_Reference_A1_String() {
		sheet.set("A1", "'a string'");
		sheet.set("A2", "=A1");
		String result = sheet.evaluate("A2");
		assertEquals("a string", result);
	}

	@Test
	public void testAssignment_A2_Reference_A1_Integer() {
		sheet.set("A1", "123");
		sheet.set("A2", "=A1");
		String result = sheet.evaluate("A2");
		assertEquals("123", result);
	}

	@Test
	public void testAssignment_Reference_Invalid_Error() {
		sheet.set("A1", "=A5");
		String result = sheet.evaluate("A1");
		assertEquals("#Error", result);
	}

	@Test
	public void testAssignment_Reference_Circular() {
		sheet.set("A1", "=A2");
		sheet.set("A2", "=A1");
		String result = sheet.evaluate("A1");
		assertEquals("#Circular", result);
	}

	@Test
	public void testAssignment_Reference_Circular_Depth_2() {
		sheet.set("A1", "=A3");
		sheet.set("A2", "=A1");
		sheet.set("A3", "=A2");
		String result = sheet.evaluate("A1");
		assertEquals("#Circular", result);
	}
	
	@Test
	public void testAssignment_Add_1_2_Result_3() {
		sheet.set("A1", "=1+2");
		String result = sheet.evaluate("A1");
		assertEquals("3", result);
	}
}
