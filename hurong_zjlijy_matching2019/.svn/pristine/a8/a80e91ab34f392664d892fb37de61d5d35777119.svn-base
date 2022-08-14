package hry.trade.comparator;

import java.util.Comparator;

import hry.trade.redis.model.EntrustTrade;


public class AscByIdEntrustComparator  implements Comparator<EntrustTrade>{

	@Override
	public int compare(EntrustTrade o1, EntrustTrade o2) {
		if(o1.getId().compareTo(o2.getId())==-1){  
            return 1;  
        }else if(o1.getId().compareTo(o2.getId())==1){  
            return -1;  
        }else{  
            return 0;  
        }  
	}

}
