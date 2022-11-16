/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.AddFilter;

import java.io.IOException;

import Framework.CommonFilterImpl;

public class AddFilter extends CommonFilterImpl{
    @Override
    public boolean specificComputationForFilter() throws IOException {
        int numOfBlank = 0;
        int idx = 0;
        int idx_17651 = 0;
        int idx_17652 = 0;
        byte[] buffer = new byte[64];
        boolean CS_17651 = false;
        boolean CS_17652 = false;
        int byte_read = 0;
        
        while(true) {          
        	// check "CS" on byte_read from student information
            while(byte_read != '\n' && byte_read != -1) {
            	byte_read = in.read();
                if(byte_read == ' ') numOfBlank++; //7
                if(byte_read != -1) buffer[idx++] = (byte)byte_read;
                
                if(numOfBlank != 0 && buffer[idx-5] == '1' && buffer[idx-4] == '7' && buffer[idx-3] == '6' 
                		&& buffer[idx-2] == '5' && buffer[idx-1] == '1') {
                	CS_17651 = true;
                	idx_17651 = idx-5;
                }
                
                if(numOfBlank != 0 && buffer[idx-5] == '1' && buffer[idx-4] == '7' && buffer[idx-3] == '6' 
                		&& buffer[idx-2] == '5' && buffer[idx-1] == '2') {
                	CS_17652 = true;
                	idx_17652 = idx-5;
                }
                   
            } 
            
            if(CS_17651&& CS_17652) {
                for(int i = 0; i<idx; i++) {
                	if(idx_17651<=i && i<=idx_17651+5) continue;
                	if(idx_17652<=i && i<=idx_17652+5) continue;
                	out.write((char)buffer[i]);
                }
                
            }else if(CS_17651 == false && CS_17652 == true){
            	for(int i = 0; i<idx; i++) {
            		if(idx_17652<=i && i<=idx_17652+5) continue;
            		out.write((char)buffer[i]);
            	}
            }else if(CS_17651== true && CS_17652 == false) {
            	for(int i = 0; i<idx; i++) {
            		if(idx_17651<=i && i<=idx_17651+5) continue;
            		out.write((char)buffer[i]);
            	}
            }else if(CS_17651== false && CS_17652 == false){
            	for(int i = 0; i<idx; i++) {
            		 out.write((char)buffer[i]);
            	}
            }
            CS_17651 = false;
            CS_17652 = false;
            if (byte_read == -1) return true;
            idx = 0;
            numOfBlank = 0;
            byte_read = '\0';
        }
    }
}
