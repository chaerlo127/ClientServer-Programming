/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.AddfilterStudentNo;

import java.io.IOException;
import java.util.Arrays;

import Framework.CommonFilterImpl;

public class AddFilterStudentNo extends CommonFilterImpl{
	private String studentNo;
	public AddFilterStudentNo(String studentNo) {
		this.studentNo = studentNo;
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
                if(byte_read != -1) buffer[idx++] = (byte)byte_read;
            }
            
            buffer = Arrays.copyOf(buffer, idx);
            
            String information = new String(buffer);
            
			if (information.contains(studentNo)) {
				out.write(buffer);
			}
			if (byte_read == -1) return true;
            idx = 0;
            byte_read = '\0';
        }
    }
}
