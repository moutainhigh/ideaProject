package hry.util.freemarker;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;

import freemarker.cache.TemplateLoader;
import hry.redis.common.utils.RedisService;
import hry.util.SpringUtil;

public class DBTemplateLoader implements TemplateLoader {
	
	private final static Logger log = Logger.getLogger(DBTemplateLoader.class);
	
	
	@Override
	public Object findTemplateSource(String name) throws IOException {
		try {
			
			// 通过id查询数据库中配置的模板信息
			// 数据库表必须有一个最后更新字段用来刷新缓存,数据库中的模板保存字段为query,这里通过model.getQuery获取
			String path = name.replace("_zh", "").replace("_zh_CN", "").replace("/", ":").replace("_CN", "").replace("_en", "").replace("_en_US", "").replace("_US", "");
			//log.info(name+"----"+path);
			RedisService redisService = SpringUtil.getBean("redisService");
			String ftl = redisService.get("FrontFtl:view"+":"+path);
			log.debug("load----------FTL--->>>FrontFtl:view"+":"+path);
			if(!StringUtils.isEmpty(ftl)){
				log.debug("load-success----------FTL--->>>FrontFtl:view"+":"+path);
				return new StringTemplateSource(name, HtmlUtils.htmlUnescape(ftl), System.currentTimeMillis());
			}
			
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public long getLastModified(Object templateSource) {
		return ((StringTemplateSource) templateSource).lastModified;
	}

	@Override
	public Reader getReader(Object templateSource, String encoding) {
		return new StringReader(((StringTemplateSource) templateSource).source);
	}

	@Override
	public void closeTemplateSource(Object templateSource) throws IOException {
		// TODO Auto-generated method stub

	}

	private static class StringTemplateSource {
		private final String name;
		private final String source;
		private final long lastModified;

		StringTemplateSource(String name, String source, long lastModified) {
			if (name == null) {
				throw new IllegalArgumentException("name == null");
			}
			if (source == null) {
				throw new IllegalArgumentException("source == null");
			}
			if (lastModified < -1L) {
				throw new IllegalArgumentException("lastModified < -1L");
			}
			this.name = name;
			this.source = source;
			this.lastModified = lastModified;
		}

		public boolean equals(Object obj) {
			if (obj instanceof StringTemplateSource) {
				return name.equals(((StringTemplateSource) obj).name);
			}
			return false;
		}

		public int hashCode() {
			return name.hashCode();
		}
	}

}
