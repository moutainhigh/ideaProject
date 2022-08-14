package hry.app.lend.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hry.lend.model.kline.KlineTimes;

import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="mailto:HelloHeSir@gmail.com">Mr_He</a>
 * @Date 2018/11/16 15:17
 * 杠杆页面 时间配置项
 */
public class TimesConfig {

    public TimesConfig() {
    }

    static {
        List<Times> klineTimes = new LinkedList<>();
        //分时线
        Times oneTimes = new Times();
        oneTimes.setSlug("分时");
        oneTimes.setResolution("1");
        oneTimes.setIsMobile("!0");
        oneTimes.setChartType(3);
        klineTimes.add(oneTimes);
        //其余时段
        for (KlineTimes value : KlineTimes.values()) {
            Times times = new Times();
            times.setSlug(value.getDescription());
            times.setResolution(value.getMsg());
            klineTimes.add(times);
        }
        setKlineTimes(klineTimes);
    }

    private static List<Times> klineTimes;

    @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
    public static class Times {
        private String slug;

        private String resolution;

        private Integer chartType;

        private String isMobile;

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getResolution() {
            return resolution;
        }

        public void setResolution(String resolution) {
            this.resolution = resolution;
        }

        public Integer getChartType() {
            return chartType;
        }

        public void setChartType(Integer chartType) {
            this.chartType = chartType;
        }

        public String getIsMobile() {
            return isMobile;
        }

        public void setIsMobile(String isMobile) {
            this.isMobile = isMobile;
        }
    }

    public static List<Times> getKlineTimes() {
        return klineTimes;
    }

    public static void setKlineTimes(List<Times> klineTimes) {
        TimesConfig.klineTimes = klineTimes;
    }
}