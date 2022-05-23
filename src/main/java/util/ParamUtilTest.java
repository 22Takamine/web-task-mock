package util;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class ParamUtilTest {

    @Test
    public void testIsNullOrEmpty() throws SQLException {
    	assertEquals(true ,ParamUtil.isNullOrEmpty(null));
    	assertEquals(false ,ParamUtil.isNullOrEmpty("test"));
    }
    
    @Test
    public void testIsNumber() throws SQLException{
    	assertEquals(true, ParamUtil.isNumber("1"));
    	assertEquals(false, ParamUtil.isNumber(null));
    }
    
    @Test
    public void testCheckAndParseInt() throws SQLException{
    	assertEquals(null,ParamUtil.checkAndParseInt(null));
    	assertEquals(Integer.valueOf(1),ParamUtil.checkAndParseInt("1"));
    	
    }

}
