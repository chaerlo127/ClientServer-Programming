/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.AddFilter;

import java.io.IOException;
import java.io.PipedOutputStream;

import Framework.CommonFilterImpl;

public class AddFilter extends CommonFilterImpl{
    @Override
    public boolean specificComputationForFilter() throws IOException {
        int numOfBlank = 0;
        int idx = 0;
        byte[] buffer = new byte[64];
        boolean CS_23456 = false;
        int byte_read = 0;
        
        while(true) {          
        	// check "CS" on byte_read from student information
            while(byte_read != '\n' && byte_read != -1) {
            	byte_read = in.read();
                if(byte_read == ' ') numOfBlank++; //7
                if(byte_read != -1) buffer[idx++] = (byte)byte_read;
                
                if(numOfBlank != 0 && buffer[idx-5] == '2' && buffer[idx-4] == '3' && buffer[idx-3] == '4' 
                		&& buffer[idx-2] == '5' && buffer[idx-1] == '6') {
                	CS_23456 = true;
                }
                   
            } 
            
            if(CS_23456) {
                for(int i = 0; i<idx; i++) 
                    out.write((char)buffer[i]);
                CS_23456 = false;
            }else if(CS_23456 == false && byte_read != -1) {
            	for(int i = 0; i<idx-2; i++) 
                    out.write((char)buffer[i]);
            	writeAnswer(out, '2');
            	out.write(13);
            }
            if (byte_read == -1) return true;
            idx = 0;
            numOfBlank = 0;
            byte_read = '\0';
        }
    }

	private void writeAnswer(PipedOutputStream out, char writeAnswer) throws IOException {
		out.write(' ');
		for(int i = 0; i<5; i++) {
			out.write(writeAnswer+i);
		}	
	}  
}
