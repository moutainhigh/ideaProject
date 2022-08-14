/**
 * 111
 */

package hry.admin.klg.limit.service.impl;

import hry.admin.klg.limit.model.KlgDesignatedRewardecord;
import hry.admin.klg.limit.service.KlgDesignatedRewardecordService;
import hry.util.SpringUtil;

public class DesignatedRewardRunable implements  Runnable {
    private KlgDesignatedRewardecord klgDesignatedRewardecord;
    public DesignatedRewardRunable(KlgDesignatedRewardecord designatedRewardecord){
        this.klgDesignatedRewardecord=designatedRewardecord;
    }
    @Override
    public void run() {
        KlgDesignatedRewardecordService designatedRewardecordService=SpringUtil.getBean("klgDesignatedRewardecordService");
        designatedRewardecordService.save(klgDesignatedRewardecord);
    }
}
