/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.AddCourseFilter;

import java.io.IOException;
import java.util.Arrays;

import Framework.CommonFilterImpl;

public class AddCourseFilter extends CommonFilterImpl{
	
	private String course1;
	private String course2;
	
    public AddCourseFilter(String course1, String course2) {
    	this.course1 = course1;
    	this.course2 = course2;
    }

	@Override
    public boolean specificComputationForFilter() throws IOException {
        while(true) {
        	int idx = 0;
            byte[] buffer = new byte[64];
            int byte_read = 0;
        	// check "CS" on byte_read from student information
            while(byte_read != '\n' && byte_read != -1) {
            	byte_read = in.read();
                if(byte_read != -1 && byte_read != 13 && byte_read != 10) buffer[idx++] = (byte)byte_read;
            }
            
            
            buffer = Arrays.copyOf(buffer, idx);
            
            String information = new String(buffer);
            if(!information.contains(course1) && byte_read != -1) {
            	information = information + " " + course1;
            }
           
            if(!information.contains(course2) && byte_read != -1) {
            	information = information + " " + course2;
            }
            
            buffer = information.getBytes();
            
            out.write(buffer);
            out.write(13);
            out.write(10);
            
            if (byte_read == -1) return true;
            idx = 0;
            byte_read = '\0';
        }
    }

	
}
