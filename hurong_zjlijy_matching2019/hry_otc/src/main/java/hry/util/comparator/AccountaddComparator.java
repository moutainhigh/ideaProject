package hry.util.comparator;



import hry.util.dto.Accountadd;

import java.util.Comparator;

public class AccountaddComparator implements Comparator<Accountadd>{

		@Override
		public int compare(Accountadd o1, Accountadd o2) {
			if(o1.getAccountId().compareTo(o2.getAccountId())==1){  
	            return -1;  
	        }else if(o1.getAccountId().compareTo(o2.getAccountId())==-1){  
	            return 1;  
	        }else{  
	            return 0;  
	        }  
		}


}
